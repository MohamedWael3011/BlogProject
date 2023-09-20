package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.exceptions.ErrorCode;
import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Etc.ReactType;
import com.Blog.Project.Blog.model.React;
import com.Blog.Project.Blog.model.ReturnModels.ReactCount;
import com.Blog.Project.Blog.repository.ReactRepository;
import com.Blog.Project.Blog.service.ReactService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//todo validation and authorization if any
@Service
public class ReactsServiceImpl implements ReactService {
    @Autowired
    ReactRepository reactRepository;
    @Override
    public ReactCount getReactCount(int pid){
        return new ReactCount(reactRepository.countByPidAndType(pid, ReactType.valueOf("LIKE")),
                reactRepository.countByPidAndType(pid, ReactType.valueOf("LOVE")),
                reactRepository.countByPidAndType(pid, ReactType.valueOf("HATE")));
    }
    @Override
    public List<React> listReacts(int pid){
        return listReacts( pid,0);

    }

    private List<React> listReacts(int pid,int uid){
        return reactRepository.findByPid(pid);

    }

    @Override
    public React addReact(React react){
//        react.setUid(uid);
        return reactRepository.save(react);
    }

    @Override
    public void delReact(int uid,int pid){
        reactRepository.deleteById(new React.CompositeKey(uid,pid));
    }
    @Override
    public React editReact(React react,int uid) throws GeneralException {
        try {
            React oldReact = reactRepository.getReferenceByUidAndPid(uid, react.getPid());
            react.setUid(uid);
            reactRepository.saveAndFlush(react);
        } catch (EntityNotFoundException var4) {
            throw new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no React with that ID");
        } catch (Exception var5) {
            throw new GeneralException(ErrorCode.INVALID_DATA,"Invalid Data");
        }
        return react;
    }
//    @Override
//    public Page<React> getReactsWithPage(int pid,int offset, int pageSize) {
//        Page<React> reacts = reactRepository.findAllByRidPid(pid,PageRequest.of(offset,pageSize));
//        return reacts;
//    }

    @Override
    public ReactType getReactStatus(int pid,int uid){
        React react = reactRepository.findByUidAndPid(uid,pid);
        return react.getType();
    }

}
