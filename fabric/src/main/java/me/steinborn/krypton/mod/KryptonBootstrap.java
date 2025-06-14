package me.steinborn.krypton.mod;

import com.velocitypowered.natives.util.Natives;
import me.steinborn.krypton.mod.shared.KryptonSharedBootstrap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KryptonBootstrap implements ModInitializer {
    private final Logger LOGGER = LoggerFactory.getLogger(KryptonBootstrap.class);

    static {
        // By default, Netty allocates 16MiB arenas for the PooledByteBufAllocator. This is too much
        // memory for Minecraft, which imposes a maximum packet size of 2MiB! We'll use 4MiB as a more
        // sane default.
        //
        // Note: io.netty.allocator.pageSize << io.netty.allocator.maxOrder is the formula used to
        // compute the chunk size. We lower maxOrder from its default of 11 to 9. (We also use a null
        // check, so that the user is free to choose another setting if need be.)
        if (System.getProperty("io.netty.allocator.maxOrder") == null) {
            System.setProperty("io.netty.allocator.maxOrder", "9");
        }
    }

    @Override
    public void onInitialize() {
        KryptonSharedBootstrap.run(FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT));
    }
}
