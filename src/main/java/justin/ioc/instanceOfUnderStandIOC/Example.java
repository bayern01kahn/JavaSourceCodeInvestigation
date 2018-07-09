package justin.ioc.instanceOfUnderStandIOC;

public class Example {

	// 此时 Example 依赖于 DataFinder 接口，同时依赖于其实现

	private DataFinder dataFinder;

	public Example() {
		dataFinder = new MysqlDataFinder();
	}
	
	public Example(DataFinder dataFinder){
        this.dataFinder = dataFinder;
    }

	public Example(int type) {
		if (type == 1) {
			dataFinder = new MysqlDataFinder();
		} else if (type == 2) {
			dataFinder = new OracleDataFinder();
		}
	}

	public void doStuff() {
		// 此处具体实现省略
		dataFinder.getData();
	}
}
