#!/usr/bin/env bash

echo "Interpret verbatim.arvo file."

if [ $# -eq 0 ]
  then
    echo "Please supply a data resource UID"
    exit 1
fi

if [[ !  -d "/data/pipelines-data" ]]
then
    echo "/data/pipelines-data does not exists on your filesystem."
fi

if [[ !  -f "/data/pipelines-data/$1/1/verbatim.avro" ]]
then
    echo "/data/pipelines-data/$1/1/verbatim.avro does not exists on your filesystem. Have you ran ingest ?"
fi

echo $(date)
java -Xmx8g -Xmx8g -XX:+UseG1GC  -cp pipelines/target/pipelines-1.0-SNAPSHOT-shaded.jar au.org.ala.pipelines.beam.ALAVerbatimToInterpretedPipeline \
    --datasetId=$1 \
    --attempt=1 \
    --interpretationTypes=ALL \
    --runner=SparkRunner \
    --targetPath=/data/pipelines-data \
    --inputPath=/data/pipelines-data/$1/1/verbatim.avro \
    --metaFileName=interpretation-metrics.txt \
    --properties=pipelines.properties \
    --useExtendedRecordId=true \
    --skipRegisrtyCalls=true
echo $(date)