package com.thang.account_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

//    Khoa chinh da ton tai
//Khi bạn cố gắng chèn hoặc cập nhật một giá trị trong bảng mà không tồn tại trong bảng khác mà nó tham chiếu đến
//Vi phạm ràng buộc duy nhất (Unique Constraint Violation): Khi bạn cố gắng chèn một giá trị vào một cột có ràng buộc duy nhất, nhưng giá trị đó đã tồn tại.

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> conflictData(Exception e) {
        logger.info(e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("code", "409");
        map.put("error", "Conflict Data");
        return map;
    }

//    Phương thức không được hỗ trợ: Khi endpoint không được định nghĩa với phương thức tương ứng (ví dụ: không có phương thức @PostMapping cho yêu cầu POST).
//    Đường dẫn không chính xác: Nếu có một lỗi trong cấu hình routing, yêu cầu có thể không được ánh xạ đúng đến phương thức xử lý.
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, String> methodNotAllowed(Exception e) {
        logger.info(e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("code", "405");
        map.put("error", "Method Not Allowed");
        return map;
    }
//    cmccmc.ổi kiểu dữ liệu: Khi một trường trong yêu cầu không thể chuyển đổi sang kiểu dữ liệu mong đợi (ví dụ: số nguyên nhưng gửi chuỗi).
//Vi phạm ràng buộc xác thực: Khi một hoặc nhiều trường trong đối tượng không đáp ứng các tiêu chí xác thực (ví dụ: @NotNull, @Size, @Email, v.v.).
//    Dữ liệu không hợp lệ:
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> badRequest(Exception e) {
        logger.info(e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("code", "400");
        map.put("error", "Parameter are not valid");
        return map;
    }

}
