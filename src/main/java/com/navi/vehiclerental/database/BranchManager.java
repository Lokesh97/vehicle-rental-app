package com.navi.vehiclerental.database;

import com.navi.vehiclerental.exceptions.BranchAlreadyExistsException;
import com.navi.vehiclerental.exceptions.BranchNotExistsException;
import com.navi.vehiclerental.models.Branch;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("branchManager")
public class BranchManager {
    private Map<String, Branch> branchMap;

    public BranchManager() {
        this.branchMap = new HashMap<>();
    }

    public Branch getBranch(String name){
        if(!this.branchMap.containsKey(name)){
            throw new BranchNotExistsException();
        }
        return this.branchMap.get(name);
    }

    public synchronized Boolean addBranch(Branch branch){
        if(this.branchMap.containsKey(branch.getId())){
            throw new BranchAlreadyExistsException();
        }
        this.branchMap.put(branch.getId(), branch);
        return Boolean.TRUE;
    }

    public synchronized Boolean removeBranch(String name){
        if(!this.branchMap.containsKey(name)){
            throw new BranchNotExistsException();
        }
        this.branchMap.remove(name);
        return Boolean.TRUE;
    }
}
