package cn.github.howwrite.whiterose.action;

import com.pi4j.io.gpio.*;

/**
 * 二驱车组件，使用l298n马达驱动板控制两侧的马达
 *
 * @author 朱森林
 * @date 2020/12/27 10:47 下午
 */
public class TwoWheel {
    private final GpioController gpioController;
    private final GpioPinDigitalOutput enableA;
    private final GpioPinDigitalOutput enableB;
    private final GpioPinDigitalOutput in1;
    private final GpioPinDigitalOutput in2;
    private final GpioPinDigitalOutput in3;
    private final GpioPinDigitalOutput in4;

    public TwoWheel(int enableAAddress, int in1Address, int in2Address, int enableBAddress, int in3Address, int in4Address) {
        this.gpioController = GpioFactory.getInstance();
        this.enableA = gpioController.provisionDigitalOutputPin(RaspiPin.getPinByAddress(enableAAddress));
        this.in1 = gpioController.provisionDigitalOutputPin(RaspiPin.getPinByAddress(in1Address));
        this.in2 = gpioController.provisionDigitalOutputPin(RaspiPin.getPinByAddress(in2Address));
        this.enableB = gpioController.provisionDigitalOutputPin(RaspiPin.getPinByAddress(enableBAddress));
        this.in3 = gpioController.provisionDigitalOutputPin(RaspiPin.getPinByAddress(in3Address));
        this.in4 = gpioController.provisionDigitalOutputPin(RaspiPin.getPinByAddress(in4Address));
    }

    /**
     * 前进
     *
     * @return 执行是否成功
     */
    public boolean advance() {
        return leftAdvance() && rightAdvance();
    }

    public boolean leftAdvance() {
        enableA.setState(PinState.HIGH);
        in1.setState(PinState.HIGH);
        in2.setState(PinState.LOW);
        return true;
    }

    public boolean rightAdvance() {
        enableB.setState(PinState.HIGH);
        in3.setState(PinState.HIGH);
        in4.setState(PinState.LOW);
        return true;
    }

    public boolean leftBack() {
        enableA.setState(PinState.HIGH);
        in1.setState(PinState.LOW);
        in2.setState(PinState.HIGH);
        return true;
    }

    public boolean rightBack() {
        enableB.setState(PinState.HIGH);
        in3.setState(PinState.LOW);
        in4.setState(PinState.HIGH);
        return true;
    }

    public boolean leftStop() {
        enableA.setState(PinState.LOW);
        in1.setState(PinState.LOW);
        in2.setState(PinState.LOW);
        return true;
    }

    public boolean rightStop() {
        enableB.setState(PinState.LOW);
        in3.setState(PinState.LOW);
        in4.setState(PinState.LOW);
        return true;
    }

    /**
     * 停止
     *
     * @return 停止是否成功
     */
    public boolean stop() {
        return leftStop() && rightStop();
    }

    public boolean back() {
        return leftBack() && rightBack();
    }

    public boolean turnLeft() {
        return leftAdvance() && rightStop();
    }

    public boolean turnRight() {
        return leftStop() && rightAdvance();
    }

    public boolean shutdown() {
        if (!gpioController.isShutdown()) {
            gpioController.shutdown();
        }
        return true;
    }
}
