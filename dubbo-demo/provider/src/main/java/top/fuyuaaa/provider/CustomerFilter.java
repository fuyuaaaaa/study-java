package top.fuyuaaa.provider;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;

/**
 * @author : fuyuaaa
 * @date : 2020-08-28 16:17
 */
@Activate(group = Constants.PROVIDER)
public class CustomerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        System.out.println("自定义filter");
        return invoker.invoke(invocation);
    }
}
