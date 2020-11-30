package jsonBenchmarks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;


public class JsonDeserialization {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static byte[] dataSerialize;

    static {
        try {
            String path = "/Users/a18535673/Projects/jmh_lab/src/main/java/jsonBenchmarks/JsonDtoInJson.json";
            dataSerialize = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void deserializationThroughput() throws IOException {
        JsonDto result = objectMapper.readValue(dataSerialize, JsonDto.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void deserializationAvgTime() throws IOException {
        JsonDto result = objectMapper.readValue(dataSerialize, JsonDto.class);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JsonDeserialization.class.getSimpleName())
                .threads(4)
                .warmupIterations(5)
                .measurementIterations(7)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}