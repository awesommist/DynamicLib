package dynamics.api;

import net.minecraft.block.Block;

public interface INeighbourAwareTile {
    void onNeighbourChanged(Block block);
}