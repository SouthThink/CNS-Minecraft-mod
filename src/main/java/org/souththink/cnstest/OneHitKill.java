package org.souththink.cnstest;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class OneHitKill {
    boolean isOpen = true;
    public OneHitKill() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            //判断攻击者是否是玩家
            if (isOpen) {
                if (source.getAttacker() != null && source.getAttacker() instanceof ServerPlayerEntity) {
                    //给予大量伤害
                    entity.damage((ServerWorld) entity.getWorld(), source, 1000000);
                    String name = entity.getName().getString();
                    ((ServerPlayerEntity) source.getAttacker()).sendMessage(Text.of("你杀死了" + name + "!!!"), false);
                    //防止生物没死
                    entity.kill((ServerWorld) entity.getWorld());
                }
            }
        });
    }
    public void start() {
        isOpen = true;
    }
    public void stop() {
        isOpen = false;
    }
    public boolean isOpen() {
        return isOpen;
    }
}
