package dynamics.entity;

import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import dynamics.sync.*;

public abstract class SyncedEntity extends Entity implements ISyncMapProvider {

    protected SyncMapEntity<SyncedEntity> syncMap;

    protected SyncedEntity(World world) {
        super (world);
        syncMap = new SyncMapEntity<SyncedEntity>(this);
        createSyncedFields();
        SyncObjectScanner.INSTANCE.registerAllFields(syncMap, this);

        syncMap.addSyncListener(new ISyncListener() {
            @Override
            public void onSync(Set<ISyncableObject> changes) {}
        });
    }

    protected abstract void createSyncedFields();

    @Override
    protected void entityInit() {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        syncMap.writeToNBT(tag);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        syncMap.readFromNBT(tag);
    }

    public void sync() {
        syncMap.sync();
    }

    @Override
    public SyncMap<SyncedEntity> getSyncMap() {
        return syncMap;
    }
}