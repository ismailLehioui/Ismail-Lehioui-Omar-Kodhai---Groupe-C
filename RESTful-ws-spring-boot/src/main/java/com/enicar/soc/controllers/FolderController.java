package com.enicar.soc.controllers;

import com.enicar.soc.entities.Folder;
import com.enicar.soc.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {
    @Autowired
    FolderService folderService;

    @GetMapping
    public ResponseEntity<List<Folder>> getAllFolders(){
        List<Folder> folders = folderService.getAllFolders();
        return ResponseEntity.ok(folders);
    }
    @GetMapping("/search")
    public ResponseEntity<Folder> getFolderByName(@RequestParam("name") String name){
        Folder f = folderService.getFolderByName(name);
        return ResponseEntity.ok(f);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Folder> getFolderById(@PathVariable("id") Long id){
        return ResponseEntity.ok(folderService.getFolderById(id));
    }
    @PostMapping
    public ResponseEntity<Folder> addFolder(@RequestBody Folder folder){
        return ResponseEntity.ok(folderService.addFolder(folder));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Folder> deleteFolder(@PathVariable("id") Long id){
        return ResponseEntity.ok(folderService.deleteFolder(id));
    }
}
