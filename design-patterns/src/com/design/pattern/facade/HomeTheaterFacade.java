package com.design.pattern.facade;
/**
 * 外观模式 Facade
 * HomeTheaterFacade
 * 适用于管理全部流程的家电
 */
public class HomeTheaterFacade {
    
    // 属性是实例
    private TheaterLight theaterLight;
	private Popcorn popcorn;
	private Stereo stereo;
	private Projector projector;
	private Screen screen;
	private DVDPlayer dVDPlayer;

    // 构造器模式就生成了这么多实例
    public HomeTheaterFacade() {
        super();
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.stereo = Stereo.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.dVDPlayer = DVDPlayer.getInstance();
    }

    /**
     * 第1步：准备工作
     */
    public void ready() {
        System.out.println("****开始准备工作****");
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dVDPlayer.on();;
        theaterLight.dim();
        System.out.println("****结束准备工作****");
    }
    /**
     * 第2步：开始播放
     */
    public void play() {
        System.out.println("****开始播放****");
        dVDPlayer.play();
        System.out.println("****结束播放****");
    }
     /**
     * 第3步：暂停
     */
    public void pause() {
        dVDPlayer.pause();
    }

     /**
     * 第4步：结束
     */
    public void end() {
        System.out.println("****开始关闭...****");
        popcorn.off();
        theaterLight.bright();
        screen.up();
        projector.off();
        stereo.off();
        dVDPlayer.off(); 
        System.out.println("****已经关闭...****");
    }

}
