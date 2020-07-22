package Do;

import org.dreambot.api.methods.map.Area;
import woodcutter.MainClass;
import org.dreambot.api.methods.map.Tile;
public class Areas {
    //Variables
    //https://explv.github.io/
    Area EastVarrokbank = new Area(3250,3422,3256,3420);
    Area WestVarrokbank = new Area(3181,3442,3185,3435);
    Area GrandExchangeBank = new Area(3160, 3493, 3168, 3483);
    Area DraynorBank = new Area(3092, 3246, 3097, 3240);
    Area Edgevillagebank = new Area(3091, 3498, 3099, 3487);
    Area FaladorEastbank = new Area(3009, 3358, 3019, 3355);
    Area DuelArenaBank = new Area(3379, 3266, 3384, 3272);
    Area RimmingtonShop = new Area(2947, 3217, 2950, 3212);

    Area EastVarrockTree = new Area(new Tile[] {
            new Tile(3295, 3391, 0),
            new Tile(3288, 3391, 0),
            new Tile(3288, 3408, 0),
            new Tile(3286, 3409, 0),
            new Tile(3277, 3408, 0),
            new Tile(3274, 3411, 0),
            new Tile(3274, 3419, 0),
            new Tile(3278, 3419, 0),
            new Tile(3278, 3426, 0),
            new Tile(3274, 3426, 0),
            new Tile(3274, 3437, 0),
            new Tile(3274, 3437, 0),
            new Tile(3271, 3437, 0),
            new Tile(3271, 3464, 0),
            new Tile(3263, 3472, 0),
            new Tile(3263, 3485, 0),
            new Tile(3310, 3485, 0),
            new Tile(3313, 3478, 0),
            new Tile(3311, 3476, 0),
            new Tile(3311, 3472, 0),
            new Tile(3314, 3472, 0),
            new Tile(3317, 3464, 0),
            new Tile(3316, 3464, 0),
            new Tile(3309, 3456, 0),
            new Tile(3296, 3456, 0)
    });
    Area GrandExchangeTree = new Area(
            new Tile[] {
                    new Tile(3142, 3516, 0),
                    new Tile(3157, 3516, 0),
                    new Tile(3162, 3512, 0),
                    new Tile(3168, 3512, 0),
                    new Tile(3171, 3516, 0),
                    new Tile(3188, 3516, 0),
                    new Tile(3197, 3507, 0),
                    new Tile(3193, 3501, 0),
                    new Tile(3189, 3497, 0),
                    new Tile(3199, 3497, 0),
                    new Tile(3200, 3474, 0),
                    new Tile(3186, 3474, 0),
                    new Tile(3186, 3468, 0),
                    new Tile(3184, 3461, 0),
                    new Tile(3184, 3450, 0),
                    new Tile(3169, 3428, 0),
                    new Tile(3150, 3416, 0),
                    new Tile(3129, 3415, 0),
                    new Tile(3114, 3420, 0),
                    new Tile(3109, 3420, 0),
                    new Tile(3116, 3430, 0),
                    new Tile(3115, 3438, 0),
                    new Tile(3106, 3444, 0),
                    new Tile(3105, 3453, 0),
                    new Tile(3106, 3459, 0),
                    new Tile(3137, 3458, 0),
                    new Tile(3137, 3466, 0),
                    new Tile(3144, 3468, 0),
                    new Tile(3139, 3473, 0),
                    new Tile(3139, 3481, 0),
                    new Tile(3142, 3484, 0),
                    new Tile(3142, 3491, 0),
                    new Tile(3139, 3495, 0),
                    new Tile(3139, 3513, 0)
            }
    );
    Area GrandExchangeOak = new Area(3189,3467,3197,3458);
    Area EastVarrockOak = new Area(3274,3438,3285,3414);
    Area SouthVarrockOak = new Area(3200,3373,3217,3358);
    Area SouthFaladorOak = new Area(2999,3318,3013,3298);
    Area WestDaynorWillow = new Area(3056,3256,3064,3251);
    Area EastDaynorWillow = new Area(3158,3277,3183,3260);
    Area SouthDaynorWillow = new Area(3081, 3238, 3090, 3225);
    Area SouthRimmingtonWillow = new Area(2957,3201,2975,3191);
    Area NorthLumbrigeWillow = new Area(3219,3311,3224,3297);
    Area EdviligeYew = new Area(3085,3479,3091,3468);
    Area ExchangeYew = new Area(3201,3506,3224,3498);
    Area LumbrigeYew = new Area(3134,3265,3141,3226);
    Area MagicTrainingMagicTree = new Area(3353, 3324, 3373, 3294);
    Area ClosestBank = new Area();
    private MainClass mainClass;
    private Factory _factory;
    public Areas(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    //Getters
    public Area getEastVarrokbank() {
        return EastVarrokbank;
    }

    public Area getWestVarrokbank() {
        return WestVarrokbank;
    }

    public Area getGrandExchangeBank() {
        return GrandExchangeBank;
    }

    public Area getDraynorBank() {
        return DraynorBank;
    }

    public Area getEdgevillagebank() {
        return Edgevillagebank;
    }

    public Area getFaladorEastbank() {
        return FaladorEastbank;
    }

    public Area getDuelArenaBank() {
        return DuelArenaBank;
    }

    public Area getRimmingtonShop() {
        return RimmingtonShop;
    }

    public Area getGrandExchangeTree() {
        return GrandExchangeTree;
    }

    public Area getEastVarrockTree() {
        return EastVarrockTree;
    }

    public Area getGrandExchangeOak() {
        return GrandExchangeOak;
    }

    public Area getEastVarrockOak() {
        return EastVarrockOak;
    }

    public Area getSouthVarrockOak() {
        return SouthVarrockOak;
    }

    public Area getSouthFaladorOak() {
        return SouthFaladorOak;
    }

    public Area getWestDaynorWillow() {
        return WestDaynorWillow;
    }

    public Area getEastDaynorWillow() {
        return EastDaynorWillow;
    }

    public Area getSouthDaynorWillow() {
        return SouthDaynorWillow;
    }

    public Area getSouthRimmingtonWillow() {
        return SouthRimmingtonWillow;
    }
    public Area getNorthLumbrigeWillow() {
        return NorthLumbrigeWillow;
    }
    public Area getEdviligeYew() {
        return EdviligeYew;
    }

    public Area getExchangeYew() {
        return ExchangeYew;
    }

    public Area getLumbrigeYew() {
        return LumbrigeYew;
    }

    public Area getMagicTrainingMagicTree() {
        return MagicTrainingMagicTree;
    }
    public Area GetClosesBank(){return ClosestBank;}

    //Setters
    public void SetClosesBank(Area Bank){ClosestBank = Bank;}

}
