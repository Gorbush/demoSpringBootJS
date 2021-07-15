package com.demo.demoSpringBootJS.service;

import com.demo.demoSpringBootJS.model.ScriptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ServiceMapJSImpl implements ServiceMapJS {

    // Хранилище ScriptInfo
    private static final Map<Integer, ScriptInfo> SCRIPT_INFO_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID ScriptInfo
    private static final AtomicInteger SCRIPT_INFO_ID_HOLDER = new AtomicInteger();

    @Override
    public ScriptInfo create(String scriptText) {
        // Create new entry for Queue
        ScriptInfo scriptInfo = new ScriptInfo();
        // Generate new ID
        final int scripInfoId = SCRIPT_INFO_ID_HOLDER.incrementAndGet();
        scriptInfo.setId(scripInfoId);
        scriptInfo.setTimeAdded(new Date());
        scriptInfo.setTextScript(scriptText);
        scriptInfo.setStatus(ScriptInfo.ScriptStatus.ENQUEUED);
        SCRIPT_INFO_REPOSITORY_MAP.put(scripInfoId, scriptInfo);

        return scriptInfo;
    }

    @Override
    public List<ScriptInfo> readAll() {
        return new ArrayList<>(SCRIPT_INFO_REPOSITORY_MAP.values());
    }

    public synchronized ScriptInfo fetchNextToRun() {
        Optional<ScriptInfo> info = SCRIPT_INFO_REPOSITORY_MAP.values().stream()
                .filter( si -> si.getStatus() == ScriptInfo.ScriptStatus.ENQUEUED).findFirst();
        if (info.isPresent()) {
            info.get().setStatus(ScriptInfo.ScriptStatus.RUNNING);
            info.get().setTimeStarted(new Date());
            return info.get();
        }
        return null;
    }

    @Override
    public ScriptInfo read(int id) {
        return SCRIPT_INFO_REPOSITORY_MAP.get(id);
    }

    @Override
    public ScriptInfo readStatus(int id) {
        return SCRIPT_INFO_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(ScriptInfo scriptInfo, int id) {
        return false;
    }

    @Override
    public boolean updateStart(int id) {
        ScriptInfo scriptInfo = read(id);
        if (scriptInfo == null) return false;
        scriptInfo.setStatus(ScriptInfo.ScriptStatus.RUNNING);
        scriptInfo.setTimeStarted(new Date());
        SCRIPT_INFO_REPOSITORY_MAP.put(id, scriptInfo);
        return true;
    }

    @Override
    public boolean updateFinish(ScriptInfo scriptInfo, String result, ScriptInfo.ScriptStatus status) {
        if (scriptInfo == null) return false;
        scriptInfo.setTimeFinished(new Date());
        scriptInfo.setResult(result);
        scriptInfo.setStatus(status);
        return true;
    }

    @Override
    public boolean delete(int id) { //delete without return item
        ScriptInfo scriptInfo = read(id);
        if (scriptInfo.getStatus() == ScriptInfo.ScriptStatus.RUNNING) {
            System.out.println("the Script  cannot be deleted, It is running now"); // script running
            return false;
        } else {
            SCRIPT_INFO_REPOSITORY_MAP.remove(id);
            return true; // all successfully
        }
    }
}
