1. 我在构建任务2CarianStyle项目时基本上没遇到什么值得记忆的问题，基本上就是拉去构建测试一次过
2. 在任务3的fabric模组的开发中问题就比较多了，由于我直接使用了最新的MC和fabric版本导致我使用的AI以及网上的教程的可用度都比较低。当时想用entity.damage来对生物造成伤害时发现AI给的获取伤害类型的方式（DamageSource.伤害类型）完全不可用，甚至连damage的用法都时少一个ServerWorld参数。至于我为什么不用entity.kill,因为我想直接还原攻击的类型而不是所有的死法都一样，后面就换成直接从攻击来源的类型来造成伤害了。
3. 遇到过的最大的问题其实是IDEA创建项目的时候默认的Gradle是8.11，而fabric最新却需要8.12+，找了半天才找到要在gradle-wrapper.properties中修改gradle的下载链接。而我把环境变量里的gradle更新了……
4. 指令是我自己想加的，本来想写gamerule的，太麻烦了。后面给自己的模组单开一条指令感觉也很不错。
