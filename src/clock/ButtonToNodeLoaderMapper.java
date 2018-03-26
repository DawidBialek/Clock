package clock;

import java.util.HashMap;
import java.util.Map;

public class ButtonToNodeLoaderMapper {
	static private Map<AppState,NodeLoader> map;
	static {
		map = new HashMap();
	}
	static void add(AppState state, NodeLoader nodeLoader) {
		map.put(state, nodeLoader);
	}
	
	static NodeLoader get(AppState state) {
		return map.get(state);
	}
}
