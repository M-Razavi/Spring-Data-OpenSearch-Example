FROM opensearchproject/opensearch:1.1.0

# this is only needed for locale development, Amazon OpenSearch Service domains come
# prepackaged with plugins from the OpenSearch community. The service automatically
# deploys and manages plugins
# https://docs.aws.amazon.com/opensearch-service/latest/developerguide/supported-plugins.html

RUN /usr/share/opensearch/bin/opensearch-plugin install analysis-phonetic
