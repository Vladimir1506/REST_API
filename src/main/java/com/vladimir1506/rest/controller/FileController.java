package com.vladimir1506.rest.controller;

import com.vladimir1506.rest.implementation.MyFileImpl;
import com.vladimir1506.rest.model.MyFile;

import java.util.List;

public class FileController {
    private final MyFileImpl fileImpl;

    public FileController() {
        this.fileImpl = new MyFileImpl();
    }

    public List<MyFile> getAllFiles() {
        return fileImpl.getAll();
    }

    public MyFile getFileById(Long id) {
        return fileImpl.getById(id);
    }

    public MyFile saveFile(MyFile file) {
        return fileImpl.save(file);
    }

    public void deleteFile(Long id) {
        fileImpl.delete(id);
    }
}
