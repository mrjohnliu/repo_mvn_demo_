package com.liuke.springbatch.demo.com;

import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListener implements org.springframework.batch.core.ChunkListener{

	@Override
	public void beforeChunk(ChunkContext context) {
		System.out.println(context.getStepContext().getStepName()+":do beforeStep");
	}

	@Override
	public void afterChunk(ChunkContext context) {
		System.out.println(context.getStepContext().getStepName()+":do afterChunk");
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		System.out.println(context.getStepContext().getStepName()+":do afterChunkError");
	}

}
