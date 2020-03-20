package justin.designPattern.command;


import justin.designPattern.command.invoker.SuperRemoteControlWithUndo;
import justin.designPattern.command.receiver.AirConditioning;
import justin.designPattern.command.receiver.Light;

/**
 * 客户端
 * @author justin
 *
 */
public class ClientMainTest {

	public static void main(String[] args) {

		System.out.println("命令模式： 模拟 遥控器 控制空调风速（低，中，高，宏命令，关，撤销） 和 灯 （开，关，撤销）");
		
		//接收者 空调和灯
		AirConditioning ac = new AirConditioning("客厅");
		Light light = new Light();
		
		//调用者，这里使用遥控器作为调用者
		SuperRemoteControlWithUndo remoteControl = new SuperRemoteControlWithUndo();
		remoteControl.setACCommand(ac);
		remoteControl.setLightCommand(light);

		
		System.out.println("\n\n遥控空调");
		
		remoteControl.onButtonClick(3);
		remoteControl.onButtonClick(4);//宏命令 ，包含多个单个命令的集合，顺序执行
		remoteControl.undoButtonClick(ac);
		
		System.out.println("\n\n遥控灯");
		
		remoteControl.onButtonClick(5);
		remoteControl.undoButtonClick(light);
		remoteControl.onButtonClick(6);
		remoteControl.undoButtonClick(light);
		
	}

}
