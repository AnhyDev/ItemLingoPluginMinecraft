package ink.anh.lingo.command;

import ink.anh.api.messages.MessageType;
import ink.anh.api.messages.Messenger;
import ink.anh.api.nbt.NBTExplorer;
import ink.anh.lingo.AnhyLingo;
import ink.anh.lingo.Permissions;

import java.util.Arrays;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtList;

/**
 * Class handling the NBT (Named Binary Tag) related subcommands for the AnhyLingo plugin.
 * This class provides functionality to manipulate and retrieve NBT data from items in-game.
 */
public class NBTSubCommand {
	
	private AnhyLingo lingoPlugin;

    /**
     * Constructor for NBTSubCommand.
     *
     * @param plugin The AnhyLingo plugin instance.
     */
	public NBTSubCommand(AnhyLingo plugin) {
		this.lingoPlugin = plugin;
	}

    /**
     * Executes the appropriate NBT related command based on the arguments.
     *
     * @param sender The sender of the command.
     * @param args Arguments passed with the command.
     * @return true if the command was successfully executed, otherwise false.
     */
    boolean execNBT(CommandSender sender, String[] args) {
        if (args.length < 2) {
            return false;
        }
        switch (args[1].toLowerCase()) {
            case "set":
                return set(sender, args);
            case "list":
                return list(sender);
            case "info":
                return info(sender, args);
        }
        return false;
    }

    private boolean set(CommandSender sender, String[] args) {
        ItemStack itemInHand = validatePlayerWithPermissionAndGetItemInHand(sender, Permissions.NBT_SET);
        if (itemInHand == null) {
            return false;
        }

        // Перевіряємо, чи передано достатньо аргументів
        if (args.length < 4) {
            sendMessage(sender, "lingo_err_command_format /lingo nbt set <nbt_key> <params...>", MessageType.WARNING);
            return false;
        }

        String nbtKey = args[2];
        String nbtValueString = String.join(" ", java.util.Arrays.copyOfRange(args, 3, args.length));

        // Встановлюємо значення NBT
        NBTExplorer.setNBTValueFromString(itemInHand, nbtKey, nbtValueString);

        sendMessage(sender, "lingo_NBT_value_set ", MessageType.NORMAL);
        return true;
    }

    private boolean list(CommandSender sender) {
        ItemStack itemInHand = validatePlayerWithPermissionAndGetItemInHand(sender, Permissions.NBT_LIST);
        if (itemInHand == null) {
            return false;
        }

        // Отримуємо NBT-теги для предмета
        NbtCompound compound = NBTExplorer.getNBT(itemInHand);

        if (compound == null || compound.getKeys().isEmpty()) {
            sendMessage(sender, "lingo_err_item_no_NBT_tags ", MessageType.WARNING);
            return true;
        }

        // Виводимо список ключів
        sendMessage(sender, "lingo_NBT_keys_for_item ", MessageType.NORMAL);
        for (String key : compound.getKeys()) {
            sendMessage(sender, "- " + key, MessageType.ESPECIALLY);
        }
        for (String key : compound.getKeys()) {
        	lingoPlugin.getLogger().info("- " + key);
        }

        return true;
    }

    private boolean info(CommandSender sender, String[] args) {
        ItemStack itemInHand = validatePlayerWithPermissionAndGetItemInHand(sender, Permissions.NBT_INFO);
        if (itemInHand == null) {
            return false;
        }

        // Перевіряємо, чи передано ключ NBT-тегу
        if (args.length < 3) {
            sendMessage(sender, "lingo_err_command_format /lingo nbt info <nbt_key>", MessageType.WARNING);
            return false;
        }

        String nbtKey = args[2];

        // Отримуємо NBT-теги для предмета
        NbtCompound compound = NBTExplorer.getNBT(itemInHand);

        if (compound == null || !compound.containsKey(nbtKey)) {
            sendMessage(sender, "lingo_err_NBT_tag_not_exist ", MessageType.WARNING);
            return true;
        }

        // Виводимо значення NBT-тегу
        String value = nbtValueToString(compound, nbtKey);
        sendMessage(sender, "NBT-" + nbtKey + ": " + value, MessageType.NORMAL);

        // Відправляємо інформацію в логи серверу
        lingoPlugin.getLogger().info("NBT-" + nbtKey + " for player " + sender.getName() + ": " + value);

        return true;
    }

    private ItemStack validatePlayerWithPermissionAndGetItemInHand(CommandSender sender, String permission) {
        // Перевіряємо, чи є викликач команди гравцем
        if (!(sender instanceof Player)) {
            sendMessage(sender, "lingo_err_command_only_player ", MessageType.ERROR);
            return null;
        }

        Player player = (Player) sender;

        // Перевіряємо наявність дозволу
        if (!player.hasPermission(permission)) {
            sendMessage(sender, "lingo_err_not_have_permission ", MessageType.ERROR);
            return null;
        }

        // Перевіряємо, чи має гравець предмет в руці
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand == null || itemInHand.getType().isAir()) {
            sendMessage(sender, "lingo_err_have_nothing_hand ", MessageType.WARNING);
            return null;
        }

        return itemInHand;
    }

    /**
     * Converts the NBT value associated with the given key in the provided NbtCompound to a String.
     * This method handles various types of NBT data and formats them into a human-readable string.
     * It's useful for displaying NBT data in commands or logs.
     *
     * @param compound The NbtCompound from which to retrieve the value.
     * @param key The key corresponding to the NBT value to convert.
     * @return A String representation of the NBT value, or null if the key does not exist or the value is null.
     */
    public String nbtValueToString(NbtCompound compound, String key) {
        if (compound == null || !compound.containsKey(key)) {
            return null;
        }

        Object value = compound.getObject(key);
        if (value instanceof Byte) {
            return value.toString();
        } else if (value instanceof Double) {
            return value.toString();
        } else if (value instanceof Float) {
            return value.toString();
        } else if (value instanceof Integer) {
            return value.toString();
        } else if (value instanceof Long) {
            return value.toString();
        } else if (value instanceof Short) {
            return value.toString();
        } else if (value instanceof byte[]) {
            return Arrays.toString((byte[]) value);
        } else if (value instanceof NbtCompound) {
            return value.toString();
        } else if (value instanceof int[]) {
            return Arrays.toString((int[]) value);
        } else if (value instanceof long[]) {
            return Arrays.toString((long[]) value);
        } else if (value instanceof float[]) {
            return Arrays.toString((float[]) value);
        } else if (value instanceof double[]) {
            return Arrays.toString((double[]) value);
        } else if (value instanceof String) {
            return (String) value;
        } else if (value instanceof NbtList<?>) {
            return value.toString(); 
        } else {
            return value != null ? value.toString() : "null";
        }
    }

	private static void sendMessage(CommandSender sender, String message, MessageType type) {
    	Messenger.sendMessage(AnhyLingo.getInstance().getGlobalManager(), sender, message, type);
    }

}
