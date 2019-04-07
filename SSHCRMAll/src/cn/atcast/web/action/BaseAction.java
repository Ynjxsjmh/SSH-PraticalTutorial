package cn.atcast.web.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private T model;

	// 创建action实例，压入栈顶，调用getModel方法获取模型对象实例，再将模型对象实例压入栈顶
	@Override
	public T getModel() {

		return model;
	}

	// 在构造方法获取模型对象类型，通过反射进行实例化
	public BaseAction() {
		try {
			// 比如：实例化SystemAction extends BaseAction<BaseDictVo>
			// 获取父类类型 ，比如：BaseAction<BaseDictVo>
			Type genericSuperclass = this.getClass().getGenericSuperclass();
			if (genericSuperclass instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
				// 获取类型中的参数
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				Type type = actualTypeArguments[0];
				// 得到模型对象的Class<T>类型
				Class<T> modelClass = (Class<T>) type;
				// 通过反射进行实例化
				model = modelClass.newInstance();

			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
