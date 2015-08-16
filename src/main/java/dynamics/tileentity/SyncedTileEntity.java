/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.tileentity;

import java.io.IOException;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import dynamics.Log;
import dynamics.sync.*;
import io.netty.buffer.ByteBuf;

public abstract class SyncedTileEntity extends DynamicTileEntity implements ISyncMapProvider {

    protected SyncMapTile<SyncedTileEntity> syncMap;

    public SyncedTileEntity() {
        syncMap = new SyncMapTile<SyncedTileEntity>(this);
        createSyncedFields();
        SyncObjectScanner.INSTANCE.registerAllFields(syncMap, this);

        syncMap.addSyncListener(new ISyncListener() {
            @Override
            public void onSync(Set<ISyncableObject> changes) {
                markUpdated();
            }
        });
    }

    protected ISyncListener createRenderUpdateListener() {
        return new ISyncListener() {
            @Override
            public void onSync(Set<ISyncableObject> changes) {
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        };
    }

    protected ISyncListener createRenderUpdateListener(final ISyncableObject target) {
        return new ISyncListener() {
            @Override
            public void onSync(Set<ISyncableObject> changes) {
                if (changes.contains(target)) worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        };
    }

    protected abstract void createSyncedFields();

    public void addSyncedObject(String name, ISyncableObject obj) {
        syncMap.put(name, obj);
    }

    public void sync() {
        syncMap.sync();
    }

    @Override
    public SyncMap<SyncedTileEntity> getSyncMap() {
        return syncMap;
    }

    @Override
    public Packet getDescriptionPacket() {
        try {
            ByteBuf payload = syncMap.createPayload(true);
            return SyncChannelHolder.createPacket(payload);
        } catch (IOException e) {
            Log.severe(e, "Error during description packet creation");
            return null;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        syncMap.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        syncMap.readFromNBT(tag);
    }
}