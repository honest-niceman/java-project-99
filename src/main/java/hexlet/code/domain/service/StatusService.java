package hexlet.code.domain.service;

import hexlet.code.domain.dtos.StatusRequest;
import hexlet.code.domain.dtos.StatusResponse;
import hexlet.code.domain.mappers.StatusMapper;
import hexlet.code.domain.model.Status;
import hexlet.code.domain.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    public StatusResponse findById(Long id) {
        Status status = statusRepository.findById(id).orElseThrow();
        return statusMapper.toDto(status);
    }

    public List<StatusResponse> findAll() {
        return statusRepository.findAll().stream().map(statusMapper::toDto).toList();
    }

    public StatusResponse save(StatusRequest statusRequest) {
        Status status = statusMapper.toEntity(statusRequest);
        Status saved = statusRepository.save(status);
        return statusMapper.toDto(saved);
    }

    public StatusResponse updateById(Long id, StatusRequest statusRequest) {
        Status status = statusRepository.findById(id).orElseThrow();
        Status updated = statusMapper.partialUpdate(statusRequest, status);
        Status saved = statusRepository.save(updated);
        return statusMapper.toDto(saved);
    }

    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }
}
