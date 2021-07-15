package com.demo.demoSpringBootJS.schedulerMapJS;


import com.demo.demoSpringBootJS.model.ScriptInfo;
import com.demo.demoSpringBootJS.service.ServiceMapJS;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private ServiceMapJS serviceMapJS;

    /**
     * Using fixedDelay to maintain the time between executions - to run scripts one by one
     */
    @Scheduled(fixedDelay = 1000)
    public void execNewScript() {
        ScriptInfo scriptToRun = serviceMapJS.fetchNextToRun();
        if (scriptToRun != null){
            String script = scriptToRun.getTextScript();
            try (Context context = Context.create()) {
                Value value = context.eval("js", script);
                Value result = value.execute();
                serviceMapJS.updateFinish(scriptToRun, result.asString(), ScriptInfo.ScriptStatus.COMPLETE);
            } catch (Exception e) {
                String stacktrace = ExceptionUtils.getStackTrace(e);
                serviceMapJS.updateFinish(scriptToRun, stacktrace, ScriptInfo.ScriptStatus.FAILED);
           }
       }
    }
}

