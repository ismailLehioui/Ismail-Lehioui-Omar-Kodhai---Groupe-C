package com.enicar.soc.services;

import com.enicar.soc.entities.Folder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFolderService {

    Folder addFolder(Folder f);

    Folder getFolderByName(String name);

    Folder getFolderById(Long id);

    List<Folder> getAllFolders();

    Folder deleteFolder(Long id);
}
