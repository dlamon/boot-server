package com.example.boot.server.util;

import com.example.boot.server.pojo.vo.ResultVO;

/**
 * @author LiaoWei
 */
public class ResultUtil {
    public static <E> ResultVO<E> success(E e) {
        ResultVO<E> resultVO = new ResultVO<>();
        resultVO.setCode("OK");
        resultVO.setMessage("成功");
        resultVO.setData(e);
        return resultVO;
    }

    public static <E> ResultVO<E> success() {
        return success(null);
    }

    public static <E> ResultVO<E> error(String code, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(MessageUtil.getMessage(code));
        if(msg != null && !"".equals(msg)) {
            sb.append("[").append(msg).append("]");
        }
        String message = sb.toString();

        ResultVO<E> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static <E> ResultVO<E> error(String code) {
        return error(code, null);
    }
}
