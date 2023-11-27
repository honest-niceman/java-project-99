package hexlet.code.app.domain.service;

import hexlet.code.app.domain.dtos.StatusResponse;
import hexlet.code.app.domain.mappers.StatusMapper;
import hexlet.code.app.domain.model.Status;
import hexlet.code.app.domain.repository.StatusRepository;
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

    public long count() {
        return statusRepository.count();
    }

    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }
}
