package cn.atcast.crm.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class RequestToBean {

	/**
	 * @param <T>
	 * @param newSource
	 *            现将要设置新值的对象
	 * @param source
	 *            源数据对象
	 */
	public static <T> void beanConvert(T newSource, T source) {
		try {
			BeanUtils.copyProperties(newSource, source);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 *            请求对象
	 * @param obj
	 *            要设置Bean的类型,传入试为: Bean.class
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanToRequest(HttpServletRequest request, Class<T> clazz) {

		// 获取页面所有的请求参数名称
		Enumeration<String> enume = request.getParameterNames();
		T beanObj = getObjectByClass(clazz);
		try {
			while (enume.hasMoreElements()) {
				// 参数名称
				String propertyName = enume.nextElement();
				// 判断是否存在此属性
				if (isCheckBeanExitsPropertyName(clazz, propertyName)) {
					// 获取请求值
					Object propertyValue = request.getParameter(propertyName);
					setProperties(beanObj, propertyName, propertyValue);
				}

			}
		} catch (Exception e) {
		}

		return beanObj;
	}

	private static <T> T getObjectByClass(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return t;
	}

	/**
	 * @param clazz
	 *            Class对象
	 * @param propertyName
	 *            属性名称
	 * @return true || false 检查对象中是否存在该属性名称
	 */
	private static boolean isCheckBeanExitsPropertyName(Class<?> clazz, String propertyName) {
		boolean retValue = false;
		try {
			Field field = clazz.getDeclaredField(propertyName);
			if (null != field) {
				retValue = true;
			}
		} catch (NoSuchFieldException e) {
			System.out.println(
					"类: " + clazz.getSimpleName() + ",不存在属性名: " + propertyName + " ,详细错误信息: " + e.getMessage());
		}
		return retValue;

	}

	/**
	 * 设置字段值
	 * 
	 * @param obj
	 *            实例对象
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            新的字段值
	 * @return
	 */
	public static void setProperties(Object object, String propertyName, Object value)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
		Method methodSet = pd.getWriteMethod();
		methodSet.invoke(object, value);
	}

}
