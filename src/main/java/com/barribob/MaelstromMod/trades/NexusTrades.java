package com.barribob.MaelstromMod.trades;

import java.util.Random;

import com.barribob.MaelstromMod.init.ModBlocks;
import com.barribob.MaelstromMod.init.ModItems;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class NexusTrades
{
    public static class Flintlock implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.GOLD_INGOT, 5);
	private ItemStack cost = new ItemStack(Items.IRON_INGOT, 6);
	private ItemStack reward = new ItemStack(ModItems.FLINTLOCK, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class Repeater implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.REDSTONE, 16);
	private ItemStack cost = new ItemStack(Items.IRON_INGOT, 6);
	private ItemStack reward = new ItemStack(ModItems.REPEATER, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class Rifle implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.IRON_INGOT, 6);
	private ItemStack cost = new ItemStack(ModItems.ELK_JERKY, 4);
	private ItemStack reward = new ItemStack(ModItems.RIFLE, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class FrostSword implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.DIAMOND_SWORD, 1);
	private ItemStack cost = new ItemStack(ModItems.ELK_HIDE, 4);
	private ItemStack reward = new ItemStack(ModItems.FROST_SWORD, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class VenomDagger implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.DIAMOND_SWORD, 1);
	private ItemStack cost = new ItemStack(Items.FERMENTED_SPIDER_EYE, 8);
	private ItemStack reward = new ItemStack(ModItems.VENOM_DAGGER, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class NexusBattleaxe implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.EMERALD, 4);
	private ItemStack cost = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 8);
	private ItemStack reward = new ItemStack(ModItems.NEXUS_BATTLEAXE, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class LeapStaff implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.RABBIT_FOOT, 1);
	private ItemStack cost = new ItemStack(Items.DIAMOND, 1);
	private ItemStack reward = new ItemStack(ModItems.LEAP_STAFF, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class FireballStaff implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.BLAZE_ROD, 8);
	private ItemStack reward = new ItemStack(ModItems.FIREBALL_STAFF, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, reward));
	}
    }
    
    public static class SpeedStaff implements ITradeList
    {
	private ItemStack base = new ItemStack(ModItems.ELK_HIDE, 8);
	private ItemStack cost = new ItemStack(Items.DIAMOND, 1);
	private ItemStack reward = new ItemStack(ModItems.SPEED_STAFF, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class StrawHat implements ITradeList
    {
	private ItemStack base = new ItemStack(Item.getItemFromBlock(Blocks.HAY_BLOCK), 2);
	private ItemStack cost = new ItemStack(Items.DIAMOND, 2);
	private ItemStack reward = new ItemStack(ModItems.STRAW_HAT, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class SpeedBoots implements ITradeList
    {
	private ItemStack base = new ItemStack(Items.DIAMOND_BOOTS, 1);
	private ItemStack cost = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 1);
	private ItemStack reward = new ItemStack(ModItems.SPEED_BOOTS, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class MaelstromHelmet implements ITradeList
    {
	private ItemStack base = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 3);
	private ItemStack reward = new ItemStack(ModItems.MAELSTROM_HELMET, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, reward));
	}
    }
    
    public static class MaelstromChestplate implements ITradeList
    {
	private ItemStack base = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 5);
	private ItemStack reward = new ItemStack(ModItems.MAELSTROM_CHESTPLATE, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, reward));
	}
    }
    
    public static class MaelstromLeggings implements ITradeList
    {
	private ItemStack base = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 4);
	private ItemStack reward = new ItemStack(ModItems.MAELSTROM_LEGGINGS, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, reward));
	}
    }
    
    public static class MaelstromBoots implements ITradeList
    {
	private ItemStack base = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 2);
	private ItemStack reward = new ItemStack(ModItems.MAELSTROM_BOOTS, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, reward));
	}
    }
    
    public static class Pumpkin implements ITradeList
    {
	private ItemStack base = new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK), 1);
	private ItemStack prank = new ItemStack(Item.getItemFromBlock(Blocks.PUMPKIN), 1);
	private ItemStack reward = new ItemStack(ModItems.PUMPKIN, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, prank, reward));
	}
    }
    
    public static class Elucidator implements ITradeList
    {
	private ItemStack base = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 9);
	private ItemStack reward = new ItemStack(ModItems.ELUCIDATOR, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, reward));
	}
    }
    
    public static class DragonSlayer implements ITradeList
    {
	private ItemStack base = new ItemStack(Item.getItemFromBlock(Blocks.IRON_BLOCK), 8);
	private ItemStack cost = new ItemStack(ModItems.AZURE_MAELSTROM_CORE_CRYSTAL, 3);
	private ItemStack reward = new ItemStack(ModItems.DRAGON_SLAYER, 1);
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, cost, reward));
	}
    }
    
    public static class MoreComing implements ITradeList
    {
	private ItemStack base = new ItemStack(Item.getItemFromBlock(Blocks.BEDROCK), 1).setStackDisplayName("More Items Coming Soon!");
	
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
	{
	    recipeList.add(new MerchantRecipe(base, base));
	}
    }
}
