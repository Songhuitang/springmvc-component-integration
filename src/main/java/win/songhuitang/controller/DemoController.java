package win.songhuitang.controller;

import org.springframework.web.bind.annotation.*;
import win.songhuitang.model.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by simon.song on 2017/4/2.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class DemoController {

    //Log usages.
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "say/{name}", method = RequestMethod.GET)
    public Message say(@PathVariable String name) {
        Message message = new Message();
        message.setName(name);
        message.setText("hello," + name);
        logger.info(">>>>>>>>>>>>>>>TEXT {}", message.getText());
        return message;
    }

}
