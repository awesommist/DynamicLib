/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.config.game;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import dynamics.item.ItemDynamicBlock;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterBlock {
    String DEFAULT = "[default]";
    String NONE = "[none]";

    @interface RegisterTileEntity {
        String name();

        Class<? extends TileEntity> cls();
    }

    String name();

    public Class<? extends ItemBlock> itemBlock() default ItemDynamicBlock.class;

    Class<? extends TileEntity> tileEntity() default TileEntity.class;

    RegisterTileEntity[] tileEntities() default {};

    String unlocalizedName() default DEFAULT;

    boolean isEnabled() default true;

    boolean isConfigurable() default true;
}