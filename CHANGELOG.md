# 1.0.0-alpha.2

### ⭐ Overview
This release contains major improvements related to events and localization support.

#### Events

Command events no more depends (extends) on the JDA's upstream events. 
`SlashCommandEvent`, `PrefixCommandEvent` and `HybridCommandEvent` are now proxies/containers for source events 
(`SlashCommandInteractionEvent`, `MessageReceivedEvent`), which allows us to isolate unnecessary methods.
Interaction with events has become more native and clean.

**Note:** `HybridCommandEvent` doesn't having at the moment `reply**` methods, 
because it needs the unified interface between `ReplyCallbackAction` and `MessageCreateAction`. Wait for updates.

#### Localization

4 new annotations for slash/hybrid commands have been added:
* `@NameLocalizations`
* `@DescriptionLocalizations`
* `@Localization` (used inside two above)
* `@CommandLocalizationFunction`

Example:
```java
@ExtraSlashCommand(description = "Standard description")
// Localizations will be displayed depending on language of the Discord client
@NameLocalizations({
        @Localization(locale = DiscordLocale.GERMAN, string = "hallo"), 
        @Localization(locale = DiscordLocale.RUSSIAN, string = "привет")
})
@DescriptionLocalizations({
        @Localization(locale = DiscordLocale.GERMAN, string = "Beschreibung auf Deutsch"),
        @Localization(locale = DiscordLocale.RUSSIAN, string = "Описание на русском")
})
// This allows you to localize the entire command including name, description and options
// It uses ResourceBundleLocalizationFunction under the hood
// See https://github.com/discord-jda/JDA/blob/master/src/examples/java/LocalizationExample.java#L21 for explanation
@CommandLocalizationFunction(baseName = "localizations", locales = {
        DiscordLocale.GERMAN, DiscordLocale.RUSSIAN
})
public class SlashCommandExample extends SlashCommand {
    @ExtraMainCommand
    public void hello(SlashCommandEvent event, @SlashOption(name = "name", description = "Your name") String name) {
        event.replyFormat("Hello, %s!", name).queue();
    }
}
```

And the same with hybrid commands. 
Keep in mind that this only works when hybrid command is called as a slash (obviously).

### 🐬 Features
* Make SlashCommandData proxy for OptionData ([#131](https://github.com/DWolf-19/JDA-Extra/pull/131))
* Make events proxies for source events ([#132](https://github.com/DWolf-19/JDA-Extra/pull/132))
* Add localization support ([#134](https://github.com/DWolf-19/JDA-Extra/pull/134))

### 📦 Dependencies
* Bump gradle/gradle-build-action from 2 to 3 ([#123](https://github.com/DWolf-19/JDA-Extra/pull/123), [#127](https://github.com/DWolf-19/JDA-Extra/pull/127))
* Bump Gradle from 8.5 to 8.6 ([#125](https://github.com/DWolf-19/JDA-Extra/pull/125))

### 🧪 Quality
* Cleanup hybrid commands handling from duplicated code ([#133](https://github.com/DWolf-19/JDA-Extra/pull/133))

### 🔑 Docs
* Fix javadoc title ([#128](https://github.com/DWolf-19/JDA-Extra/pull/128))
* Fix Java toolchain in samples ([#130](https://github.com/DWolf-19/JDA-Extra/pull/130))

### 📕 Metadata
* Add getting started section to README ([#126](https://github.com/DWolf-19/JDA-Extra/pull/126))
* Fix warning display in README ([#129](https://github.com/DWolf-19/JDA-Extra/pull/129))
* Enable Gradle caching in workflows ([#135](https://github.com/DWolf-19/JDA-Extra/pull/135))
* Run CI on workflow update ([#136](https://github.com/DWolf-19/JDA-Extra/pull/136))

# 1.0.0-alpha.1

### ⭐ Overview
This release contains changes starting from first commit and introducing some core concepts of internal work and interacting with the framework.

#### API

You need a `JDAExtraBuilder` to configure the framework. 
You can define various settings and bind commands to `JDAExtra` instance.

```java
public class App {
    public static void main(String[] args) {
        // Create JDAExtraBuilder instance
        // Set prefix for prefix (text)/hybrid commands and that mentions of your bot will be considered as a prefix
        JDAExtraBuilder jdaExtra = new JDAExtraBuilder().setWhenMentionOr("!")
                // Bind command class(es) to JDA-Extra
                // There will be a more flexible and user-friendly API in the future, 
                // but for now just list the command classes
                .addSlashCommands(new SlashCommandExample());

        JDABuilder.createDefault("TOKEN")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                // build() returns configured instance of JDA-Extra,
                // main class which will handle events from JDA (SlashCommandInteractionEvent, etc.)
                .addEventListeners(jdaExtra.build())
                .build();
    }
}
```

This is a basic example of a command, your class should inherit from a basic abstract class like `SlashCommand`, etc.

```java
// Command annotation. Here you can define command name and description
@ExtraSlashCommand(description = "My cool command")
public class SlashCommandExample extends SlashCommand {
    @ExtraMainCommand // Command entry point. There will be a separate guide about the entry points
    public void ping(SlashCommandEvent event, @SlashOption(name = "name", description = "Your name") String name) {
        // And now you can work with all methods from SlashCommandInteractionEvent!
        event.replyFormat("Pong, %s!", name).queue();
    }
}
```

As you may have noticed, you didn't specify the command name in some annotation.
JDA-Extra took it from the method name! In our case, `ping`. 
But if you need to, you can define it via `@ExtraSlashCommand(name = "name", description = "...")`, 
command name defined in the annotation will override method name of the main entry point.

The option type is taken from parameter type. You can also redefine this in the option annotation.

**Note:** JDA-Extra can't take option name from parameter name due to limitations in the Java Reflection API, 
so you must **ALWAYS** define the option name in annotation. _Perhaps_ we will circumvent this limitation a little later.

The text command is identical:

```java
@ExtraPrefixCommand
public class PrefixCommandExample extends PrefixCommand {
    @ExtraMainCommand
    public void hello(PrefixCommandEvent event, @PrefixOption(name = "name") String name) {
        // Shortcut for getMessage().replyFormat()
        event.replyFormat("Hello, %s!", name).queue();
    }
}
```

`PrefixCommandEvent` has all `MessageReceivedEvent` methods and a lot of useful shortcuts (currently only various `reply**` methods).

#### Main killer-feature: hybrid commands

You can define:

```java
@ExtraHybridCommand(description = "My cool command")
public class HybridCommandExample extends HybridCommand {
    @ExtraMainCommand
    public void hybrid(HybridCommandEvent event, @HybridOption(name = "name", description = "Your name") String name) {
        event.replyFormat("Hello, %s!", name).queue();
    }
}
```

...and you will receive a command that is processed both as a prefix and as a slash!
You can check this by running our example and type in some channel `/hybrid DWolf_19` and `!hybrid DWolf_19`.

#### Exceptions

You can see that JDA-Extra can throw exceptions during runtime, such as `CommandNotFoundException`, etc. 
At the moment, you do not have functionality to control and handle exceptions. Just wait for updates.

#### Philosophy

Unlike for example JDA-Utilities, JDA-Extra has a different approach to work: the framework will never do what you did not set. 
There are no default mechanisms that will respond to the user with a predefined message in case of an error with the command. 
You will receive an error, its handling depends on you. JDA-Extra is a tool, in order for it to work, you need to configure it as you need. 
This does not mean that we are abandoning all the "default behavior", of course. 
One of the reasons for creating this solution was unpredictable behavior of other command-handlers, who decided for the developer what to do.

### 🐬 Features
* Add CommandClient class which implements EventListener ([#7](https://github.com/DWolf-19/JDA-Extra/pull/7))
* Add command events ([#8](https://github.com/DWolf-19/JDA-Extra/pull/8))
* Add CommandEvent interface ([#12](https://github.com/DWolf-19/JDA-Extra/pull/12))
* Add abstract command classes ([#14](https://github.com/DWolf-19/JDA-Extra/pull/14))
* Add getters to the CommandClient ([#21](https://github.com/DWolf-19/JDA-Extra/pull/21))
* Add special executeLogic method for each class ([#22](https://github.com/DWolf-19/JDA-Extra/pull/22))
* Prefix commands parsing ([#25](https://github.com/DWolf-19/JDA-Extra/pull/25))
* Backport to JDK 8 ([#27](https://github.com/DWolf-19/JDA-Extra/pull/27), [#62](https://github.com/DWolf-19/JDA-Extra/pull/62))
* Add commands options representation class ([#31](https://github.com/DWolf-19/JDA-Extra/pull/31))
* Add whenMention handling in PrefixCommandParser ([#37](https://github.com/DWolf-19/JDA-Extra/pull/37))
* Create custom InvalidHybridEventException ([#42](https://github.com/DWolf-19/JDA-Extra/pull/42))
* Add CommandNotFoundException ([#44](https://github.com/DWolf-19/JDA-Extra/pull/44))
* Rewrite with annotations ([#49](https://github.com/DWolf-19/JDA-Extra/pull/49))
* Make parser build PrefixCommandEvent ([#55](https://github.com/DWolf-19/JDA-Extra/pull/55))
* Organize main annotations ([#59](https://github.com/DWolf-19/JDA-Extra/pull/59))
* Command building refactoring ([#61](https://github.com/DWolf-19/JDA-Extra/pull/61))
* Add hybrid commands handling ([#73](https://github.com/DWolf-19/JDA-Extra/pull/73))
* Add slash commands handling ([#74](https://github.com/DWolf-19/JDA-Extra/pull/74))
* Take out the entry point build to CommandBuilder ([#76](https://github.com/DWolf-19/JDA-Extra/pull/76))
* Split Option annotation to three different ([#83](https://github.com/DWolf-19/JDA-Extra/pull/83))
* Add shortcuts for Message#reply** ([#91](https://github.com/DWolf-19/JDA-Extra/pull/91))
* Require name in **Option annotations ([#92](https://github.com/DWolf-19/JDA-Extra/pull/92))
* Add choices annotations for parameters ([#93](https://github.com/DWolf-19/JDA-Extra/pull/93))
* Add custom OptionData for prefix commands ([#94](https://github.com/DWolf-19/JDA-Extra/pull/94))
* Add custom option mappings ([#106](https://github.com/DWolf-19/JDA-Extra/pull/106))
* Add custom option data for all commands ([#107](https://github.com/DWolf-19/JDA-Extra/pull/107))
* Options support ([#108](https://github.com/DWolf-19/JDA-Extra/pull/108))

### 🔨 Enhancements
* Add JDA to dependencies ([#6](https://github.com/DWolf-19/JDA-Extra/pull/6))
* Move CommandClient to ListenerAdapter ([#11](https://github.com/DWolf-19/JDA-Extra/pull/11))
* Add JB annotations to dependencies ([#15](https://github.com/DWolf-19/JDA-Extra/pull/15))
* Rename isNsfw method to isNSFW in Command ([#20](https://github.com/DWolf-19/JDA-Extra/pull/20))
* Rename CommandClient to JDAExtra ([#32](https://github.com/DWolf-19/JDA-Extra/pull/32))
* Add base class for command exceptions ([#46](https://github.com/DWolf-19/JDA-Extra/pull/46))
* Rename models to entities ([#48](https://github.com/DWolf-19/JDA-Extra/pull/48))
* Move prefix commands specific classes to sep dir ([#52](https://github.com/DWolf-19/JDA-Extra/pull/52))
* Rename domain to dwolfnineteen.com ([#65](https://github.com/DWolf-19/JDA-Extra/pull/65))
* Make gradlew executable ([#80](https://github.com/DWolf-19/JDA-Extra/pull/80))
* Rename Command abs class to BaseCommand ([#81](https://github.com/DWolf-19/JDA-Extra/pull/81))
* Rework and fix hybrid/prefix command handling ([#95](https://github.com/DWolf-19/JDA-Extra/pull/95))
* Add unified interface for option data ([#96](https://github.com/DWolf-19/JDA-Extra/pull/96))
* Update project to JDK 21, but leave compile at 8 ([#121](https://github.com/DWolf-19/JDA-Extra/pull/121))

### 🐛 Bugfixes
* Fix JDAExtra class name ([#34](https://github.com/DWolf-19/JDA-Extra/pull/34))
* Fix PrefixCommandModel NPE in JDAExtra ([#57](https://github.com/DWolf-19/JDA-Extra/pull/57))

### 📦 Dependencies
* Bump net.dv8tion:JDA from 5.0.0-beta.10 to 5.0.0-beta.20 ([#17](https://github.com/DWolf-19/JDA-Extra/pull/17), [#39](https://github.com/DWolf-19/JDA-Extra/pull/39), [#84](https://github.com/DWolf-19/JDA-Extra/pull/84), [#100](https://github.com/DWolf-19/JDA-Extra/pull/100), [#102](https://github.com/DWolf-19/JDA-Extra/pull/102), [#110](https://github.com/DWolf-19/JDA-Extra/pull/110), [#112](https://github.com/DWolf-19/JDA-Extra/pull/112), [#114](https://github.com/DWolf-19/JDA-Extra/pull/114), [#118](https://github.com/DWolf-19/JDA-Extra/pull/118), [#122](https://github.com/DWolf-19/JDA-Extra/pull/122))
* Bump Gradle from 8.1.1 to 8.5 ([#35](https://github.com/DWolf-19/JDA-Extra/pull/35), [#64](https://github.com/DWolf-19/JDA-Extra/pull/64), [#88](https://github.com/DWolf-19/JDA-Extra/pull/88), [#103](https://github.com/DWolf-19/JDA-Extra/pull/103), [#117](https://github.com/DWolf-19/JDA-Extra/pull/117))
* Bump actions/checkout from 3 to 4 ([#97](https://github.com/DWolf-19/JDA-Extra/pull/97))
* Bump org.jetbrains:annotations from 24.0.1 to 24.1.0 ([#113](https://github.com/DWolf-19/JDA-Extra/pull/113))
* Bump actions/setup-java from 3 to 4 ([#116](https://github.com/DWolf-19/JDA-Extra/pull/116))

### 🧪 Quality
* Project refactoring with Gradle Java library template ([#13](https://github.com/DWolf-19/JDA-Extra/pull/13))
* Code style fixes ([#109](https://github.com/DWolf-19/JDA-Extra/pull/109))

### 🔑 Docs
* Add examples dir ([#4](https://github.com/DWolf-19/JDA-Extra/pull/4))
* Fix args in ExampleBot.java ([#5](https://github.com/DWolf-19/JDA-Extra/pull/5))
* Add application Gradle plugin to examples ([#19](https://github.com/DWolf-19/JDA-Extra/pull/19))
* Add lib implementation in examples build.gradle ([#54](https://github.com/DWolf-19/JDA-Extra/pull/54))
* Rename examples to samples ([#67](https://github.com/DWolf-19/JDA-Extra/pull/67))
* Replace "application" plugin to "java" in samples ([#85](https://github.com/DWolf-19/JDA-Extra/pull/85))
* Add Java 8 toolchain to Gradle in samples ([#86](https://github.com/DWolf-19/JDA-Extra/pull/86))
* Write javadoc ([#120](https://github.com/DWolf-19/JDA-Extra/pull/120))

### 📕 Metadata
* Add README.md ([#1](https://github.com/DWolf-19/JDA-Extra/pull/1))
* License repository with the MIT ([#2](https://github.com/DWolf-19/JDA-Extra/pull/2))
* Ignore all build dirs in .gitignore ([#3](https://github.com/DWolf-19/JDA-Extra/pull/3))
* Add bug report issue template ([#9](https://github.com/DWolf-19/JDA-Extra/pull/9))
* Setup dependabot version updates ([#10](https://github.com/DWolf-19/JDA-Extra/pull/10))
* Fix dependabot ([#16](https://github.com/DWolf-19/JDA-Extra/pull/16))
* Update description in the README ([#23](https://github.com/DWolf-19/JDA-Extra/pull/23))
* Add banner to the README ([#24](https://github.com/DWolf-19/JDA-Extra/pull/24))
* Create feature request issue template ([#28](https://github.com/DWolf-19/JDA-Extra/pull/28))
* Add plans section to the README ([#29](https://github.com/DWolf-19/JDA-Extra/pull/29))
* Create CI action ([#33](https://github.com/DWolf-19/JDA-Extra/pull/33))
* Git files refactoring ([#36](https://github.com/DWolf-19/JDA-Extra/pull/36))
* Add github-actions to dependabot ([#40](https://github.com/DWolf-19/JDA-Extra/pull/40))
* Filter out unnecessary CI starts ([#41](https://github.com/DWolf-19/JDA-Extra/pull/41))
* Fix CI branches ([#43](https://github.com/DWolf-19/JDA-Extra/pull/43))
* Paths instead branches in CI ([#45](https://github.com/DWolf-19/JDA-Extra/pull/45))
* Add BotCommands to README ([#51](https://github.com/DWolf-19/JDA-Extra/pull/51))
* Add synchronize type to CI ([#53](https://github.com/DWolf-19/JDA-Extra/pull/53))
* .gitignore refactoring with comments ([#58](https://github.com/DWolf-19/JDA-Extra/pull/58))
* Update bug report template description ([#66](https://github.com/DWolf-19/JDA-Extra/pull/66))
* Add JDA-Extra contributors to copyright ([#68](https://github.com/DWolf-19/JDA-Extra/pull/68))
* Add copyright notice to all files ([#69](https://github.com/DWolf-19/JDA-Extra/pull/69))
* Create PR template ([#70](https://github.com/DWolf-19/JDA-Extra/pull/70))
* Add banner as a file ([#71](https://github.com/DWolf-19/JDA-Extra/pull/71))
* Reduce the banner resolution ([#72](https://github.com/DWolf-19/JDA-Extra/pull/72))
* Move PR template to .github ([#75](https://github.com/DWolf-19/JDA-Extra/pull/75))
* Add copyright notice to Gradle scripts ([#77](https://github.com/DWolf-19/JDA-Extra/pull/77))
* Run CI when changing any Gradle file ([#79](https://github.com/DWolf-19/JDA-Extra/pull/79))
* Expand warning in the README ([#82](https://github.com/DWolf-19/JDA-Extra/pull/82))
* Add "Gradle" to "Changes" in PR template ([#87](https://github.com/DWolf-19/JDA-Extra/pull/87))
* Add CoC ([#98](https://github.com/DWolf-19/JDA-Extra/pull/98))
* Add security policy ([#99](https://github.com/DWolf-19/JDA-Extra/pull/99))
* Add contributing guidelines ([#101](https://github.com/DWolf-19/JDA-Extra/pull/101))
* Thanks to JetBrains! ([#111](https://github.com/DWolf-19/JDA-Extra/pull/111))
* Add HEADER.md for comfort ([#115](https://github.com/DWolf-19/JDA-Extra/pull/115))
* Update design (thanks [@nelifs](https://github.com/nelifs)) ([#119](https://github.com/DWolf-19/JDA-Extra/pull/119))
* Configure publication to Maven ([#124](https://github.com/DWolf-19/JDA-Extra/pull/124))
