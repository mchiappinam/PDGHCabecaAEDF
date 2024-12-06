package me.mchiappinam.pdghcabeca;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comando implements CommandExecutor {
  private Main plugin;

  public Comando(Main main) {
    plugin = main;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
    if (cmd.getName().equalsIgnoreCase("head")) {
    	if(sender==plugin.getServer().getConsoleSender()) {
    		if(plugin.getServer().getPlayerExact(args[0])!=null) {
    			plugin.head(plugin.getServer().getPlayerExact(args[0]), args[1], false, null);
    		      plugin.getServer().broadcastMessage("§3§l[PDGH]§e "+plugin.getServer().getPlayerExact(args[0]).getName()+"§a pegou a cabeça de§e "+args[1]);
    	        return true;    	       
    		}
	        return true;
    	}
      if((sender.hasPermission("pdgh.op"))||sender.hasPermission("pdgh.cabeca")) {
	      if ((args.length >1) || (args.length == 0)) {
	        sender.sendMessage("§cUse /head <nick>");
	        return true;
	      }
	      plugin.head((Player)sender, args[0], false, null);
	      sender.sendMessage("§3§l[PDGH]§a Você pegou a cabeça de §e"+args[0]);
	      return true;
	    }else{
	      sender.sendMessage("§cSem permissões");
	      return true;
	    }
    }
    return true;
  }
}