package me.mchiappinam.pdghcabeca;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listeners implements Listener {
	
	private Main plugin;
	public Listeners(Main main) {
		plugin=main;
	}

	@EventHandler
	private void onDeath(PlayerDeathEvent e) {
		if ((e.getEntity().getKiller() instanceof Player)) {
	    	Player p=e.getEntity();
	    	Player killer=e.getEntity().getKiller();
	    	if(killer!=p) {
	        	Random randomgen = new Random();
	        	int i = randomgen.nextInt(plugin.chance);
	        	if(i == 1) {
	        		plugin.head(null, p.getName(), true, p.getLocation());
		    		for (Player all : plugin.getServer().getOnlinePlayers()) {
	        			all.sendMessage("§c"+p.getName()+"§e dropou a cabeça");
	            		all.playSound(all.getLocation(), Sound.PIG_DEATH, 1.0F, 1.0F);
		    		}
	        		return;
	        	}
	    		for (Player all : plugin.getServer().getOnlinePlayers())
		        	if(killer.getItemInHand().hasItemMeta()) { //!killer.getItemInHand().getItemMeta().hasDisplayName()
		        		if(!killer.getItemInHand().getItemMeta().hasDisplayName()) {
		        			all.sendMessage("§a"+killer.getName()+"§e matou §c"+p.getName());
		        		}else{
		        			all.sendMessage("§a"+killer.getName()+"§e matou §c"+p.getName()+"§e usando "+(killer.getItemInHand().getItemMeta().getDisplayName().replaceAll("	", " ").replaceAll("  ", " ")));
		        		}
		        	}else{
		        		all.sendMessage("§a"+killer.getName()+"§e matou §c"+p.getName());
		        	}
	    	}else{
	    		killer.sendMessage("§c§lSe matar no servidor é proibido! Caso continue, você será punido!");

	    		for (Player all : plugin.getServer().getOnlinePlayers()) {
	        		all.sendMessage("§a"+killer.getName()+"§e se matou");
	    		}
	    	}
		}
	}
	
    @EventHandler(priority=EventPriority.HIGHEST)
	public void onInteract(PlayerInteractEvent e) {
    	if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            BlockState block = e.getClickedBlock().getState();
            if (block instanceof Skull) {
                Skull skull = (Skull) block;
                String owner = skull.getOwner();
                if(owner==null)
                	return;
                if(owner=="")
                	return;
                player.sendMessage("§aCabeça de §e"+owner);
        		//PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText("§aCabeça de §e"+owner), (byte)2);
        		//((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
    	}
    }
}