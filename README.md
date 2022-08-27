# AntiCommand
🛑 Plugin for blocking commands on chat tab completer and in-game

### Before/After
![before](https://user-images.githubusercontent.com/28400410/175277682-14a6797d-3d03-4a0b-a5b2-0511ad4ae3dd.png)
![after](https://user-images.githubusercontent.com/28400410/175277687-6a4af49f-77dc-43df-ae8a-91c0e96a4572.png)

### Usage
Add commands that players will be able to use for selected permissions groups and reload the plugin.
<br>The plugin hides the visibility of other commands while tabing them and blocks their usage

### Example config.yml
```yml
# All commands bypass permission: 'anticommand.bypass'
groups:

  # Default group for all players, no permission needed
  default:
    - "login"
    - "register"
    - "msg"
    - "reply"
    - "r"
    - "ignore"
    - "helpop"
    - "ranks"
    - "spawn"
    - "tpa"
    - "help"
    - "plot"
    - "p"

  # Example group, need permission 'anticommand.group.admin' for use
  admin:
    - "mute"
    - "unmute"
    - "kick"
    - "ban"
    - "unban"
    - "gamemode"
    - "fly"
    - "tp"
    - "broadcast"
    - "bc"
    - "pardon"

  # Example group, need permission 'anticommand.group.headadmin' for use
  # Add 'bypass: true' to groups to allow to see ALL COMMANDS
  headadmin:
    bypass: true
```
