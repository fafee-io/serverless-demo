package io.fafee.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.fafee.serverless.dto.FooBar;
import io.fafee.serverless.dto.LambdaInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LambdaHandler implements RequestHandler<LambdaInput, FooBar> {

    @Inject
    DemoService demoService;

    @Override
    public FooBar handleRequest(LambdaInput input, Context context) {
        return demoService.get(input.getId());
    }

}
