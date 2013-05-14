package fishingCraft.common.dictionary;

import fishingCraft.shar.util.Debug;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Class responsible for adding all FCItems to the Ore dictionary.
 * The complete item dictionary name list can be found in FCOreDictionary.txt.
 * 
 *  @author Sharingan616
 *
 */
public class ItemDictionary
{
    public static void add(Item i)
    {
        String dictName = "FC." + i.getUnlocalizedName().substring("Item.".length());
        removeStringAfterChar(dictName, '#');
        OreDictionary.registerOre(dictName, i);
        Debug.print(dictName+" [ENTER]");
        //Debug.println("Added " + i + ", \"" + dictName + "\" to dictionary.");
    }

    public static boolean checkForChar(String s, char c)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == c)
            {
                return true;
            }
        }

        return false;
    }

    public static String removeStringAfterChar(String s, char c)
    {
        if (checkForChar(s, c))
        {
            return s.substring(0, s.indexOf(c));
        }

        return s;
    }
}
