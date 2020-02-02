package fr.kysio.phone.phones;

import fr.kysio.phone.ItemsManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPhone extends Item {

    public final String NAME = "telephone";

    public ItemPhone(){
        setCreativeTab(CreativeTabs.TOOLS);
        ItemsManager.setItemName(this, NAME);
    }

}
