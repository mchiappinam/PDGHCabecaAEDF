package me.mchiappinam.pdghcabeca;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public int chance = 0;
	
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHCabeça] §2ativando...");
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}
			catch(Exception e) {}
		}
		getServer().getPluginCommand("head").setExecutor(new Comando(this));
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		chance=getConfig().getInt("chance");
		getServer().getConsoleSender().sendMessage("§3[PDGHCabeça] §2chance: §6"+chance);
		getServer().getConsoleSender().sendMessage("§3[PDGHCabeça] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHCabeça] §2Acesse: http://pdgh.com.br/");
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHCabeça] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHCabeça] §2Acesse: http://pdgh.com.br/");
	}
	
	public void head(Player sender, String nome, boolean droparNoChao, Location loc) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setOwner(nome);
        skull.setItemMeta(meta);
        if(droparNoChao)
        	loc.getWorld().dropItem(loc, skull);
        else
        	sender.getInventory().addItem(new ItemStack[] { skull });
	}
}