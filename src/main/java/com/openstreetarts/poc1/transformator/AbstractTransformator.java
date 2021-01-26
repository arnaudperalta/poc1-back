package com.openstreetarts.poc1.transformator;

import java.util.List;

public interface AbstractTransformator<M, D> {
    D modelToDto(M model);

    M dtoToModel(D dto);

    List<D> modelsToDtos(List<M> model);

    List<M> dtosToModels(List<D> dto);

}
