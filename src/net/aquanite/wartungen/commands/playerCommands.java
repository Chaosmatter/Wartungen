package net.aquanite.wartungen.commands;

import net.aquanite.wartungen.Wartungen;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class playerCommands extends Command {

    private Wartungen plugin;

    public playerCommands(Wartungen plugin) {
        super("maintenance", "maintenance.command");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if(!plugin.isMaintenance()) {
            plugin.setMaintenance(true);
            p.sendMessage(new TextComponent(Wartungen.prefix + "§7The network is now in §cmaintenance-mode§7."));
            for(ProxiedPlayer online : plugin.getProxy().getPlayers()) {
                if(!online.hasPermission("maintenance.bypass")) {
                    online.disconnect(new TextComponent("§f§lAquanite.net \n \n        §cThe network is currently in maintenance."));
                }
            }
        } else {
            plugin.setMaintenance(false);
            p.sendMessage(new TextComponent(Wartungen.prefix + "§7The network is no longer in §cmaintenance-mode."));
        }
    }
}
