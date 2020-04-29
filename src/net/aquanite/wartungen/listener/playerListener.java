package net.aquanite.wartungen.listener;

import net.aquanite.wartungen.Wartungen;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

public class playerListener implements Listener {

    private Wartungen plugin;

    public playerListener(Wartungen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(ServerConnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if(p.getServer() == null) {
            if(plugin.isMaintenance()) {
                if(!p.hasPermission("maintenance.bypass")) {
                    e.getPlayer().disconnect(new TextComponent("§f§lAquanite.net \n \n        §cThe network is currently in maintenance."));
                } else {
                    e.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent e) {
        if(plugin.isMaintenance()) {
            e.getResponse().setDescription("§f§lAquanite.net §8● §eyour gaming network §f[§b1.8-1.14§f] \n     §8➥§cThe network is currently in maintenance.");
            e.getResponse().setVersion(plugin.getProtocol());
        } else {

            e.getResponse().setDescription("§f§lAquanite.net §8● §eyour gaming network §f[§b1.8-1.14§f] \n§c§lInformation §8» §5Release-Event §8● §5DOUBLE COINS");
        }
    }
}
