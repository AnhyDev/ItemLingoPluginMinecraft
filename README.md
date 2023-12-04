# AnhyLingo

<span style="box-sizing: border-box; text-decoration-line: underline;"><span style="box-sizing: border-box; font-weight: bolder;">AnhyLingo</span></span> is a plugin for Minecraft servers designed to create a multilingual interface. It allows players to receive system messages, messages from other plugins, as well as see names and descriptions of items in their chosen language.

A common limitation of many Minecraft plugins is their confinement to a single language, as defined in the plugin's configuration. Few plugins offer true multilingual support, often relying solely on the language of the Minecraft client.

<span style="box-sizing: border-box; font-weight: bolder;">AnhyLingo</span> stands out in this landscape. It not only utilizes the client's language settings but also empowers players to choose multiple languages in a specified order for finding appropriate translations. Furthermore, <span style="box-sizing: border-box; font-weight: bolder;">AnhyLingo</span> makes almost any in-game chat message multilingual by using language keys. These keys are replaced with corresponding translations during the server-to-client packet transmission, ensuring that messages are delivered in the player's chosen language.

Moreover, <span style="box-sizing: border-box; font-weight: bolder;">AnhyLingo</span> brings multilingual capabilities to scenarios where it seemed improbable. For instance, when creating quests with a quest plugin, even if the plugin itself is multilingual, the quest messages are typically restricted to one language. <span style="box-sizing: border-box; font-weight: bolder;">AnhyLingo</span> innovatively addresses this by allowing creators to use keys from custom language files, enabling the quests to be multilingual. This versatility greatly enhances the gaming experience, making it more inclusive and accessible to a diverse, global player base.

In conclusion, <span style="box-sizing: border-box; font-weight: bolder;">AnhyLingo</span> transcends the typical boundaries of language in Minecraft, offering a versatile and inclusive solution that enriches the gaming experience for a diverse, global player base.

<div class="container content-container-x" style="box-sizing: border-box; width: 1256.41px; padding: 30px; margin: auto; max-width: 95%; border-radius: 8px; box-shadow: rgba(0, 0, 0, 0.6) 0px 4px 10px; background-color: rgba(28, 28, 28, 0.7); min-width: 80%; font-family: Arial, sans-serif; font-size: 16px;">

### API:

#### Developers of other plugins can use AnhyLingo as a library to make their plugins multilingual.

The AnhyLingo API offers a robust solution for developers seeking true multilingualism in their Minecraft plugins. By using this API, system messages within plugins can be made multilingual right at the point of their creation, before being sent to the player. This method of translating keys into the respective languages is far more efficient than renaming keys in already transmitted packets. For developers aiming to achieve authentic multilingual functionality in their plugins, incorporating the AnhyLingo API is a strategic and effective choice.

Use the AnhyLingo plugin in your project as a dependency. Here are the instructions for adding the plugin using Gradle and Maven.

#### Adding Using Gradle

To use AnhyLingo in your Gradle project, add the following lines to your project's `build.gradle` file:

<div class="code-block" style="box-sizing: border-box; margin-top: 10px; text-wrap: nowrap;">

<pre style="box-sizing: border-box; font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace; font-size: 14px; margin-top: 0px; margin-bottom: 1rem; overflow: auto; color: #057c68; overflow-wrap: break-word; background-color: #050505; border: 1px solid #4e4141; border-radius: 4px; width: 1016.93px; padding: 10px 10px 10px 50px; margin-left: 50px;">dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}</pre>

</div>

<div class="code-block" style="box-sizing: border-box; margin-top: 10px; text-wrap: nowrap;">

<pre style="box-sizing: border-box; font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace; font-size: 14px; margin-top: 0px; margin-bottom: 1rem; overflow: auto; color: #057c68; overflow-wrap: break-word; background-color: #050505; border: 1px solid #4e4141; border-radius: 4px; width: 1016.93px; padding: 10px 10px 10px 50px; margin-left: 50px;">dependencies {
    implementation 'com.github.AnhyDev:AnhyLingo:v0.1.2'
}</pre>

</div>

#### Adding Using Maven

To include AnhyLingo in your Maven project, insert these lines into your `pom.xml` file:

<div class="code-block" style="box-sizing: border-box; margin-top: 10px; text-wrap: nowrap;">

<pre style="box-sizing: border-box; font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace; font-size: 14px; margin-top: 0px; margin-bottom: 1rem; overflow: auto; color: #057c68; overflow-wrap: break-word; background-color: #050505; border: 1px solid #4e4141; border-radius: 4px; width: 1016.93px; padding: 10px 10px 10px 50px; margin-left: 50px;"><repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories></pre>

</div>

<div class="code-block" style="box-sizing: border-box; margin-top: 10px; text-wrap: nowrap;">

<pre style="box-sizing: border-box; font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace; font-size: 14px; margin-top: 0px; margin-bottom: 1rem; overflow: auto; color: #057c68; overflow-wrap: break-word; background-color: #050505; border: 1px solid #4e4141; border-radius: 4px; width: 1016.93px; padding: 10px 10px 10px 50px; margin-left: 50px;"><dependency>
    <groupId>com.github.AnhyDev</groupId>
    <artifactId>AnhyLingo</artifactId>
    <version>v0.1.2</version> 
</dependency></pre>

</div>

</div>

<div class="container content-container-y" style="box-sizing: border-box; width: 1256.41px; padding: 30px; margin: auto; max-width: 95%; border-radius: 8px; min-width: 80%; font-family: Arial, sans-serif; font-size: 16px; background-color: #151515;">

### An example of using the repository

#### Class for Language Management:

<div class="code-block" style="box-sizing: border-box; margin-top: 10px; text-wrap: nowrap;">

<pre style="box-sizing: border-box; font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace; font-size: 14px; margin-top: 0px; margin-bottom: 1rem; overflow: auto; color: #057c68; overflow-wrap: break-word; background-color: #050505; border: 1px solid #4e4141; border-radius: 4px; width: 1016.93px; padding: 10px 10px 10px 50px; margin-left: 50px;">package myplugin.lang;

import myplugin.MyPlugin;
import ink.anh.lingo.api.lang.LanguageManager;

public class LangMessage extends LanguageManager {

    private static LangMessage instance = null;
    private static final Object LOCK = new Object();

    private LangMessage(MyPlugin plugin) {
        super(plugin, "lang"); // 'lang' is the name of the folder containing language files
    }

    public static LangMessage getInstance(MyPlugin plugin) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new LangMessage(plugin);
                }
            }
        }
        return instance;
    }
}</pre>

</div>

`lang` is the name of the folder containing language files.

Files must have the name format: `"xxx_[language_code].yml"`, for example, `"name_en.yml"`, files that do not meet the standard are not read.

The contents of the files must conform to the YAML standard, and have the form `key: "Value"`, where "value" is the translation for the given key in this language.

#### Initialization in the Plugin Main Class:

<div class="code-block" style="box-sizing: border-box; margin-top: 10px; text-wrap: nowrap;">

<pre style="box-sizing: border-box; font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace; font-size: 14px; margin-top: 0px; margin-bottom: 1rem; overflow: auto; color: #057c68; overflow-wrap: break-word; background-color: #050505; border: 1px solid #4e4141; border-radius: 4px; width: 1016.93px; padding: 10px 10px 10px 50px; margin-left: 50px;">import myplugin.lang.LangMessage;
import ink.anh.lingo.api.lang.LanguageManager;

public class MyPlugin extends JavaPlugin {

    private static MyPlugin instance;
    private LanguageManager languageManager;

    @Override
    public void onEnable() {
        instance = this;
        languageManager = LangMessage.getInstance(this);
    }

    public static MyPlugin getInstance() {
        return instance;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }
}
</pre>

</div>

#### Example Use in Other Classes:

<div class="code-block" style="box-sizing: border-box; margin-top: 10px; text-wrap: nowrap;">

<pre style="box-sizing: border-box; font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace; font-size: 14px; margin-top: 0px; margin-bottom: 1rem; overflow: auto; color: #057c68; overflow-wrap: break-word; background-color: #050505; border: 1px solid #4e4141; border-radius: 4px; width: 1016.93px; padding: 10px 10px 10px 50px; margin-left: 50px;">import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import ink.anh.lingo.api.Translator;
import ink.anh.lingo.messages.MessageType;
import ink.anh.lingo.messages.Messenger;
import ink.anh.lingo.utils.LangUtils;

public class OtherClass {

    private MyPlugin plugin;
    private LanguageManager languageManager;

    public OtherClass(MyPlugin plugin) {
        this.plugin = plugin;
        this.languageManager = plugin.getLanguageManager();
    }

    public static String[] checkPlayerPermissions(CommandSender sender, String permission) {
        if (sender instanceof ConsoleCommandSender) {
            return null;
        }

        String[] langs = new String[] {null};
        if (sender instanceof Player) {
            Player player = (Player) sender;
            langs = LangUtils.getPlayerLanguage(player);
            if (!player.hasPermission(permission)) {
                sendMessage(sender, Translator.translateKeyWorld("shop_err_not_have_permission", langs, MyPlugin.getInstance().getLanguageManager()), MessageType.ERROR);
                return langs;
            }
        }
        return langs;
    }

    private static void sendMessage(CommandSender sender, String message, MessageType type) {
        Messenger.sendMessage(MyPlugin.getInstance(), sender, message, type);
    }
}
</pre>

</div>

</div>
