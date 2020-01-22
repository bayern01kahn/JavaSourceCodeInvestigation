package justin.test;

import java.util.ArrayList;

public class initTest {
    public static void main(String[] args) {
        System.out.println(FinalT.NAME);
    }
}
class FinalT {
    public static final java.lang.String NAME = "stormma";
    static {
        System.out.println("FinalT初始化");
    }

}
/**
为啥没有”FinalT初始化”呢，我们先来看一下这个class文件的字节码吧！

stormma@stormma:~/coding/java-project/concurrency/target/classes/me/stormma/chapter4$ javap -verbose FinalTest.class 
Classfile /Users/stormma/coding/java-project/concurrency/target/classes/me/stormma/chapter4/FinalTest.class
  Last modified 2017-11-14; size 598 bytes
  MD5 checksum f6a69bfc19f0e693fdc57a9af831cfb8
  Compiled from "FinalTest.java"
public class me.stormma.chapter4.FinalTest
  minor version: 0
  major version: 50
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #7.#21         // java/lang/Object."<init>":()V
   #2 = Fieldref           #22.#23        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = Class              #24            // me/stormma/chapter4/FinalT
   #4 = String             #25            // stormma
   #5 = Methodref          #26.#27        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #6 = Class              #28            // me/stormma/chapter4/FinalTest
   #7 = Class              #29            // java/lang/Object
   #8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               LocalVariableTable
  #13 = Utf8               this
  #14 = Utf8               Lme/stormma/chapter4/FinalTest;
  #15 = Utf8               main
  #16 = Utf8               ([Ljava/lang/String;)V
  #17 = Utf8               args
  #18 = Utf8               [Ljava/lang/String;
  #19 = Utf8               SourceFile
  #20 = Utf8               FinalTest.java
  #21 = NameAndType        #8:#9          // "<init>":()V
  #22 = Class              #30            // java/lang/System
  #23 = NameAndType        #31:#32        // out:Ljava/io/PrintStream;
  #24 = Utf8               me/stormma/chapter4/FinalT
  #25 = Utf8               stormma
  #26 = Class              #33            // java/io/PrintStream
  #27 = NameAndType        #34:#35        // println:(Ljava/lang/String;)V
  #28 = Utf8               me/stormma/chapter4/FinalTest
  #29 = Utf8               java/lang/Object
  #30 = Utf8               java/lang/System
  #31 = Utf8               out
  #32 = Utf8               Ljava/io/PrintStream;
  #33 = Utf8               java/io/PrintStream
  #34 = Utf8               println
  #35 = Utf8               (Ljava/lang/String;)V
{
  public me.stormma.chapter4.FinalTest();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 8: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lme/stormma/chapter4/FinalTest;
  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #4                  // String stormma
         5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 11: 0
        line 12: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  args   [Ljava/lang/String;
}

SourceFile: "FinalTest.java"

其中#4 = String #25 // stormma这个地方，是个常量，所以没有触发类的初始化，所以也不会执行static块中的初始化

**/