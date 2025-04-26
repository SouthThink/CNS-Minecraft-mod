package org.souththink.cnstest;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class PlayerDeadMsg {
    private boolean isOpen = true;
    public PlayerDeadMsg() {
        System.out.println("启用了玩家死亡监听器");
        ServerLivingEntityEvents.AFTER_DEATH.register((LivingEntity player, DamageSource source) -> {
            if (isOpen){
                if (player instanceof ServerPlayerEntity) {
//                    ((ServerPlayerEntity) player).sendMessage(Text.of(player.getName().getString() + "似了！伤害类型是" + source.getName()), false);
                    ((ServerPlayerEntity) player).sendMessage(Text.of(player.getName().getString()), false);
                }
                //获取玩家周围十六个范围内的所有实体
                player.getWorld().getNonSpectatingEntities(LivingEntity.class, player.getBoundingBox().expand(16)).forEach(entity -> {
                    //在聊天框中打印所有实体以及距离
                    if (player instanceof ServerPlayerEntity) {
                        String name = entity.getName().getString();
                        String distance = String.valueOf(player.squaredDistanceTo(entity));
//                        String msg = name + " 距离为 " + distance;
                        ((ServerPlayerEntity) player).sendMessage(Text.of(name), false);
                        //一击必杀生物
//                        entity.kill((ServerWorld) player.getWorld());
                    }
                });
            }
        });
    }
    //开启监听器方法
    public void start() {
        isOpen = true;
    }
    //关闭监听器方法
    public void stop() {
        isOpen = false;
    }
    public boolean isOpen() {
        return isOpen;
    }
}
