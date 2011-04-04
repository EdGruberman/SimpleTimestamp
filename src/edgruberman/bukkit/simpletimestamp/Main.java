package edgruberman.bukkit.simpletimestamp;

import java.util.logging.Level;

import org.bukkit.event.Event;

public class Main extends org.bukkit.plugin.java.JavaPlugin {

    public Communicator communicator = new Communicator(this);
	
    public void onEnable() {
        this.communicator.log("Version " + this.getDescription().getVersion());
        
        Configuration.load(this);
        this.communicator.setLogLevel(Level.parse(this.getConfiguration().getString("logLevel", "INFO")));
        this.communicator.log(Level.CONFIG,
            "timestamp: " + this.getConfiguration().getString("timestamp")
            + "; format: " + this.getConfiguration().getString("format")
        );
        
        this.registerEvents();
        
        this.communicator.log("Plugin Enabled");
    }
    
    public void onDisable() {
        this.communicator.log("Plugin Disabled");
    }
    
    private void registerEvents() {
        PlayerListener playerListener = new PlayerListener(this);
        
        org.bukkit.plugin.PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Monitor, this);
    }
    
}
