package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.GroupRepository;
import com.learning.dao.GroupRepositorySearchCriteria;
import com.learning.dto.BranchDTO;
import com.learning.dto.GroupDTO;
import com.learning.dto.LevelDTO;
import com.learning.model.Branch;
import com.learning.model.Group;
import com.learning.model.Level;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.BranchService;
import com.learning.service.GroupService;
import com.learning.service.LevelService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private BranchService branchService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private GroupRepositorySearchCriteria groupRepositorySearchCriteria;

	@Override
	public GroupDTO save(GroupDTO groupDTO) {
		if (groupDTO.getId() != null) {
			if (!existingGroupById(groupDTO.getId(), groupDTO.getName(), groupDTO.getLevel().getId(),
					groupDTO.getBranch().getId())) {
				return null;

			}

		} else if (!existingGroup(groupDTO.getName(), groupDTO.getLevel().getId(), groupDTO.getBranch().getId())) {
			return null;

		}
		Group group = convertDTOtoModel(groupDTO);
		group = groupRepository.save(group);

		return convertModelToDTO(group);
	}

	@Override
	public GroupDTO findById(long idOut) {
		Optional<Group> optional = groupRepository.findById(idOut);

		if (optional.isPresent()) {
			Group groupFromDb = optional.get();
			return convertModelToDTO(groupFromDb);
		}
		return null;
	}

	@Override
	public void delete(Group group) {
		groupRepository.delete(group);

	}

	@Override
	public PartialList<GroupDTO> findByCriteres(Demande<GroupDTO> demande) {

		List<Group> groups = groupRepositorySearchCriteria.findByCriteres(demande);
		Long count = groupRepositorySearchCriteria.countByCriteres(demande);
		return new PartialList<GroupDTO>(count, convertEntitiesToDtos(groups));

	}

	@Override
	public Group convertDTOtoModel(GroupDTO groupDTO) {
		Group group = new Group();
		group.setId(groupDTO.getId());
		group.setName(groupDTO.getName());
		if (groupDTO.getBranch() != null) {
			group.setBranch(branchService.convertDTOtoModel(groupDTO.getBranch()));
		}
		if (groupDTO.getLevel() != null) {
			group.setLevel(levelService.convertDTOtoModel(groupDTO.getLevel()));
		}
		return group;
	}

	@Override
	public GroupDTO convertModelToDTO(Group group) {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId(group.getId());
		groupDTO.setName(group.getName());
		Branch branch = group.getBranch();
		Level level = group.getLevel();

		if (branch != null) {
			groupDTO.setBranch(branchService.convertModelToDTO(branch));
		}
		if (level != null) {
			groupDTO.setLevel(levelService.convertModelToDTO(level));
		}
		groupDTO.setCreatedAt(group.getCreatedAt());
		groupDTO.setUpdatedAt(group.getUpdatedAt());
		return groupDTO;
	}

	@Override
	public PartialList<GroupDTO> convertToListDTO(PartialList<Group> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		groupRepository.deleteById(id);

	}

	@Override
	public List<GroupDTO> convertEntitiesToDtos(List<Group> groups) {
		// basic methode
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		for (Group group : groups) {
			list.add(convertModelToDTO(group));
		}
		return list;
	}

	@Override
	public List<Group> convertDtosToEntities(List<GroupDTO> groupsDTO) {
		List<Group> list = new ArrayList<Group>();
		for (GroupDTO groupDTO : groupsDTO) {
			list.add(convertDTOtoModel(groupDTO));
		}
		return list;
	}

	@Override
	public List<GroupDTO> findByOrganization(Long idOrganization) {

		return convertEntitiesToDtos(groupRepository.findByOrganization(idOrganization));
	}

	@Override
	public boolean existingGroup(String name, Long idLevel, Long idBranch) {
		Group existGroup = groupRepository.findByNameAndLevelAndBranch(name, idLevel, idBranch);
		return existGroup == null || existGroup.getId() == null;
	}

	@Override
	public boolean existingGroupById(Long id, String name, Long idLevel, Long idBranch) {
		Group existGroup = groupRepository.findByNameAndLevelAndBranch(name, idLevel, idBranch);
		return existGroup == null || existGroup.getId().equals(id);
	}

	@Override
	public List<GroupDTO> findByUser(Long idUser) {
		return convertEntitiesToDtos(groupRepository.findByUser(idUser));
	}

	@Override
	public List<GroupDTO> findByOrganizationAndLevelAndBranch(Long idOrganization, Long idLevel, Long idBranch) {

		Demande<GroupDTO> demande = new Demande<>();
		GroupDTO group = new GroupDTO();
		group.setLevel(idLevel != 0 ? new LevelDTO(idLevel) : null);
		group.setBranch(idBranch != 0 ? new BranchDTO(idBranch) : null);
		group.setOrganizationId(idOrganization);
		demande.setModel(group);
		demande.setPage(100);
		return convertEntitiesToDtos(groupRepositorySearchCriteria.findByCriteres(demande));

	}

	@Override
	public Long countByOrganizationAndLevelAndBranch(Long idOrg, Long idLevel, Long idBranch) {
		Demande<GroupDTO> demande = new Demande<>();
		GroupDTO group = new GroupDTO();
		group.setLevel(idLevel != 0 ? new LevelDTO(idLevel) : null);
		group.setBranch(idBranch != 0 ? new BranchDTO(idBranch) : null);
		group.setOrganizationId(idOrg);
		demande.setModel(group);
		demande.setPage(100);
		return groupRepositorySearchCriteria.countByCriteres(demande);
	}

}
