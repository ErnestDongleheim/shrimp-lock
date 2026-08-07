package com.shrimplock;

import javax.inject.Inject;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Shrimp Lock",
	description = "Disables the Eat option on all food except Shrimps",
	tags = {"food", "shrimp", "eat", "menu"}
)
public class ShrimpLockPlugin extends Plugin
{
	// https://oldschool.runescape.wiki/w/Shrimps
	private static final int SHRIMPS_ITEM_ID = 315;

	@Inject
	private Client client;

	@Override
	protected void startUp()
	{
		log.info("Shrimp Lock started!");
	}

	@Override
	protected void shutDown()
	{
		log.info("Shrimp Lock stopped!");
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		if ("Eat".equals(event.getOption()) && event.getItemId() != SHRIMPS_ITEM_ID)
		{
			MenuEntry[] entries = client.getMenuEntries();
			client.setMenuEntries(Arrays.copyOf(entries, entries.length - 1));
		}
	}
}
