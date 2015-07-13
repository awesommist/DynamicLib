/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import dynamics.config.game.RegisterBlock;
import dynamics.config.game.RegisterItem;

import com.google.common.base.Throwables;

public class FieldProcessor {

    public interface FieldValueVisitor<T> {
        void visit(T value);
    }

    private static <A extends Annotation, T> void processEntries(Class<? extends InstanceContainer<T>> container, Class<? extends A> annotationCls, Class<T> fieldCls, FieldValueVisitor<T> visitor) {
        for (Field f : container.getFields()) {
            if (Modifier.isStatic(f.getModifiers()) && f.isAnnotationPresent(annotationCls)) {
                try {
                    Object value = f.get(null);
                    T item = fieldCls.cast(value);
                    if (item != null) visitor.visit(item);
                } catch (IllegalAccessException e) {
                    throw Throwables.propagate(e);
                }
            }
        }
    }

    public static void processBlocks(Class<? extends BlockInstances> container, FieldValueVisitor<Block> visitor) {
        processEntries(container, RegisterBlock.class, Block.class, visitor);
    }

    public static void processsItems(Class<? extends ItemInstances> container, FieldValueVisitor<Item> visitor) {
        processEntries(container, RegisterItem.class, Item.class, visitor);
    }
}