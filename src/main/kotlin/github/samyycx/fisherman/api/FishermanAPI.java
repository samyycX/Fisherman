package github.samyycx.fisherman.api;

import github.samyycx.fisherman.modules.economy.EconomyProvider;
import github.samyycx.fisherman.modules.economy.EconomyProviderManager;

public class FishermanAPI {

    public static void registerEconomyProvider(EconomyProvider provider) {
        EconomyProviderManager.addEconomyProvider(provider);
    }

}
