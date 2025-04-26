package org.souththink.cnstest;

import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class Cnstest implements ModInitializer {
    PlayerDeadMsg playerDeadMsg;
    OneHitKill oneHitKill;

    @Override
    public void onInitialize() {
        playerDeadMsg = new PlayerDeadMsg();
        oneHitKill = new OneHitKill();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LiteralCommandNode<ServerCommandSource> register = dispatcher.register(CommandManager.literal("cns")
                    .then(CommandManager.literal("playerdeadmsg")
                            .then(CommandManager.literal("start")
                                    .executes(context -> {
                                        playerDeadMsg.start();
                                        context.getSource().sendFeedback(() -> Text.literal("已开启玩家死亡信息获取"), false);
                                        return 1;
                                    })
                            )
                            .then(CommandManager.literal("stop")
                                    .executes(context -> {
                                        playerDeadMsg.stop();
                                        context.getSource().sendFeedback(() -> Text.literal("已关闭玩家死亡信息获取"), false);
                                        return 1;
                                    })
                            )
                            .executes(context -> {
                                        context.getSource().sendFeedback(() -> Text.literal("玩家死亡信息" + (playerDeadMsg.isOpen()?"开启中":"未开启")), false);
                                        return 1;
                                    }
                            ))
                    .then(CommandManager.literal("OneHitKill")
                            .then(CommandManager.literal("start")
                                    .executes(context -> {
                                        oneHitKill.start();
                                        context.getSource().sendFeedback(() -> Text.literal("已开启一击必杀"), false);
                                        return 1;
                                    })
                            )
                            .then(CommandManager.literal("stop")
                                    .executes(context -> {
                                        oneHitKill.stop();
                                        context.getSource().sendFeedback(() -> Text.literal("已关闭一击必杀"), false);
                                        return 1;
                                    })
                            )
                            .executes(context -> {
                                        context.getSource().sendFeedback(() -> Text.literal("一击必杀" + (oneHitKill.isOpen?"开启中":"未开启")), false);
                                        return 1;
                                    }
                            ))
                    .executes(context -> {
                        context.getSource().sendFeedback(() -> Text.literal("主命令"), false);
                        return 1;
                    }));
        });
    }
}
