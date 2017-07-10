package com.creativemd.itemphysic.packet;

import com.creativemd.creativecore.common.packet.CreativeCorePacket;
import com.creativemd.itemphysic.EventHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DropPacket extends CreativeCorePacket{
	
	public int power;
	public boolean control;
	
	public DropPacket()
	{
		power = 0;
		control = false;
	}
	
	public DropPacket(int power, boolean control)
	{
		this.power = power;
		this.control = control;
	}
	
	@Override
	public void writeBytes(ByteBuf bytes)
    {
    	bytes.writeInt(power);
    	bytes.writeBoolean(control);
    }
	
	@Override
	public void readBytes(ByteBuf bytes)
	{
		power = bytes.readInt();
		control = bytes.readBoolean();
	}
    
	@Override
	@SideOnly(Side.CLIENT)
    public void executeClient(EntityPlayer player)
    {
    	
    }
    
	@Override
    public void executeServer(EntityPlayer player)
    {
    	EventHandler.Droppower = power;
    	EntityItem item = player.dropItem(control);
    	EventHandler.Droppower = 1;
    	
    }
	
}
