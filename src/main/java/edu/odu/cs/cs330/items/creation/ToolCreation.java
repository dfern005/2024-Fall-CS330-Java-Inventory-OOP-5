package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Tool;


@SuppressWarnings({
    "PMD.AtLeastOneConstructor"
})
public class ToolCreation implements ItemCreationStrategy
{
    /**
     * Convenience wrapper to mirror the Rust new-returns-a-builder pattern.
     * Use "create" since "new" is a reserved keyword in Java.
     */
    public static ToolCreation construct()
    {
        return new ToolCreation();
    }

    @Override
    public Item fromDefaults()
    {
        // Return a **Default** Tool
        return new Tool("[Placeholder]", 0, 0, "[Default Material]", "[Default Modifier]", 0);
    }

    @Override
    public int requiredNumberOfValues()
    {
        // Replace the return value;
        return 6;
    }

    @SuppressWarnings({
        "PMD.LawOfDemeter",
        "PMD.LocalVariableCouldBeFinal"
    })
    @Override
    public Item fromTokens(final String... tokens)
    {
        if (tokens.length != requiredNumberOfValues()) {
            return null;
        }

        String name = tokens[0];
        int durability;
        int speed;
        String material = tokens[2];
        String modifier = tokens[4];
        int modifierLevel;
        try {
            durability = Integer.parseInt(tokens[1]);
            speed = Integer.parseInt(tokens[3]);
            modifierLevel = Integer.parseInt(tokens[5]);
        } catch (NumberFormatException e) {
            return null; 
        }

        return new Tool(name, durability, speed, material, modifier, modifierLevel);
    }

    @SuppressWarnings({
        "PMD.LawOfDemeter",
        "PMD.LocalVariableCouldBeFinal",
        "PMD.OnlyOneReturn"
    })
    @Override
    public Item fromExisting(final Item original)
    {
        if (!(original instanceof Tool)) {
            return null;
        }

        Tool theOriginal = (Tool) original;

        return new Tool(theOriginal.getName(), theOriginal.getDurability(), theOriginal.getSpeed(),
        theOriginal.getMaterial(), theOriginal.getModifier(), theOriginal.getModifierLevel());
    }
}
